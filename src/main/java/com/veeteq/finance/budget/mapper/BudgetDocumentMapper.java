package com.veeteq.finance.budget.mapper;

import com.veeteq.finance.budget.dto.BudgetDocumentDTO;
import com.veeteq.finance.budget.dto.BudgetDocumentItemDTO;
import com.veeteq.finance.budget.model.*;
import com.veeteq.finance.budget.repository.AccountRepository;
import com.veeteq.finance.budget.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class BudgetDocumentMapper<T> {

  private final AccountRepository accountRepository;
  private final ItemRepository itemRepository;

  @Autowired
  public BudgetDocumentMapper(AccountRepository accountRepository, ItemRepository itemRepository) {
    this.accountRepository = accountRepository;
    this.itemRepository = itemRepository;
  }

  public BudgetDocument toEntity(BudgetDocumentDTO dto) {
    if (dto == null) {
      return null;
    }

    BudgetDocument entity = switch (dto.getDocumentType()) {
      case BILL -> createBill(dto);
      case NOTE -> createNote(dto);
      case INVOICE -> createInvoice(dto);
      case TRANSFER -> createTransfer(dto);
    };
    dto.getItems().stream()
            .map(this::toEntity)
            .forEach(documentItem -> {
              documentItem.setAccount(entity.getAccount());
              documentItem.setOperationDate(entity.getDocumentDate());
              entity.addToDocumentItems(documentItem);
            });

    return entity;
  }

  private BudgetDocument<Bill> createBill(BudgetDocumentDTO dto) {
    Account account = getAccountById(dto.getAccountId());

    Bill bill = Bill.builder()
            .withId(dto.getId())
            .withDocumentDate(dto.getDocumentDate())
            .withDocumentTitle(dto.getDocumentTitle())
            .withAccount(account)
            .withPaymentMethod(dto.getPaymentMethod())
            .withCurrency(dto.getCurrency())
            .withCurrencyRate(dto.getExchangeRate())
            .build();
    return bill;
  }

  private BudgetDocument<Note> createNote(BudgetDocumentDTO dto) {
    Account account = getAccountById(dto.getAccountId());

    Note note = Note.builder()
            .withId(dto.getId())
            .withDocumentDate(dto.getDocumentDate())
            .withAccount(account)
            .withPaymentMethod(dto.getPaymentMethod())
            .withCurrency(dto.getCurrency())
            .withCurrencyRate(dto.getExchangeRate())
            .withCounterpartyId(dto.getCounterpartyId())
            .build();

    return note;
  }

  private BudgetDocument<Invoice> createInvoice(BudgetDocumentDTO dto) {
    Account account = getAccountById(dto.getAccountId());

    Invoice invoice = Invoice.builder()
            .withId(dto.getId())
            .withDocumentDate(dto.getDocumentDate())
            .withAccount(account)
            .withPaymentMethod(dto.getPaymentMethod())
            .withCurrency(dto.getCurrency())
            .withCurrencyRate(dto.getExchangeRate())
            .withCounterpartyId(dto.getCounterpartyId())
            .withInvoiceNumber(dto.getInvoiceNumber())
            .build();
    return invoice;
  }

  public BudgetDocumentItem toEntity(BudgetDocumentItemDTO dto) {

    BudgetDocumentItem budgetItem = switch (dto.getType()) {
      case EXPENSE -> createExpense(dto);
      case INCOME -> createIncome(dto);
      default -> throw new IllegalArgumentException(("Unable to map BudgetDocumentItem of type: " + dto.getType()));
    };

    Item item = getItemById(dto.getProduct().getId());
    budgetItem.setItem(item);
    return budgetItem;
  }

  private BudgetDocumentItem createExpense(BudgetDocumentItemDTO dto) {
    BudgetDocumentItem expense = new Expense()
            .setId(dto.getId())
            .setCount(dto.getCount())
            .setPrice(dto.getPrice().abs())
            .setComment(dto.getComment());
    return expense;
  }

  private BudgetDocumentItem createIncome(BudgetDocumentItemDTO dto) {
    BudgetDocumentItem income = new Income()
            .setId(dto.getId())
            .setPrice(dto.getPrice().abs())
            .setComment(dto.getComment());
    return income;
  }

  private BudgetDocument createTransfer(BudgetDocumentDTO dto) {
    return null;
  }

  private Account getAccountById(Long accountId) {
    Account account = accountRepository.getReferenceById(accountId);
    if (account == null) {
      throw new EntityNotFoundException("Could not found account with id: " + accountId);
    }
    return account;
  }

  private Item getItemById(Long itemId) {
    Item item = itemRepository.getReferenceById(itemId);
    if (item == null) {
      throw new EntityNotFoundException("Could not found item with id: " + itemId);
    }
    return item;
  }
}
