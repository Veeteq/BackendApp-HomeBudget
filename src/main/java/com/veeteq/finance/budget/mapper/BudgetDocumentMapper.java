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
public class BudgetDocumentMapper {

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

  public BudgetDocumentDTO toDto(BudgetDocument entity) {
    if (entity == null) {
      return null;
    }

    BudgetDocumentDTO dto = switch (entity.getDocumentType() ) {
      case NOTE -> fromNote(entity);
      case BILL -> fromBill(entity);
      case INVOICE -> fromInvoice(entity);
      case TRANSFER -> throw new UnsupportedOperationException("Not implemented yet");
    };
    return dto;
  }

  private BudgetDocument createBill(BudgetDocumentDTO dto) {
    Account account = getAccountById(dto.getAccountId());

    Bill bill = Bill.builder()
            .withId(dto.getId())
            .withDocumentDate(dto.getDocumentDate())
            .withDocumentTitle(dto.getDocumentTitle())
            .withAccount(account)
            .withPaymentMethod(dto.getPaymentMethod())
            .withCurrency(dto.getCurrency())
            .withCurrencyRate(dto.getExchangeRate())
            .withCounterpartyId(dto.getCounterpartyInfo().getId())
            .withBankStatementDetailId(dto.getBankStatementDetailId())
            .build();
    return bill;
  }

  private BudgetDocument createNote(BudgetDocumentDTO dto) {
    Account account = getAccountById(dto.getAccountId());

    Note note = Note.builder()
            .withId(dto.getId())
            .withDocumentDate(dto.getDocumentDate())
            .withDocumentTitle(dto.getDocumentTitle())
            .withAccount(account)
            .withPaymentMethod(dto.getPaymentMethod())
            .withCurrency(dto.getCurrency())
            .withCurrencyRate(dto.getExchangeRate())
            .withCounterpartyId(dto.getCounterpartyId())
            .build();

    return note;
  }

  private BudgetDocument createInvoice(BudgetDocumentDTO dto) {
    Account account = getAccountById(dto.getAccountId());

    Invoice invoice = Invoice.builder()
            .withId(dto.getId())
            .withDocumentDate(dto.getDocumentDate())
            .withDocumentTitle(dto.getDocumentTitle())
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

  private BudgetDocumentDTO fromNote(BudgetDocument entity) {
    Note note = (Note) entity;
    BudgetDocumentDTO dto = fromCommon(entity)
            .setCounterpartyId(note.getCounterpartyId());
    return dto;
  }

  private BudgetDocumentDTO fromBill(BudgetDocument entity) {
    Bill bill = (Bill) entity;
    BudgetDocumentDTO dto = fromCommon(entity)
            .setCounterpartyId(bill.getCounterpartyId())
            .setBankStatementDetailId(bill.getBankStatementDetailId());
    return dto;
  }

  private BudgetDocumentDTO fromInvoice(BudgetDocument entity) {
    Invoice invoice = (Invoice) entity;
    BudgetDocumentDTO dto = fromCommon(entity)
            .setCounterpartyId(invoice.getCounterpartyId())
            .setBankStatementDetailId(invoice.getBankStatementDetailId())
            .setInvoiceNumber(invoice.getInvoiceNumber());
    return dto;
  }

  private BudgetDocument createTransfer(BudgetDocumentDTO dto) {
    return null;
  }

  private BudgetDocumentDTO fromCommon(BudgetDocument entity) {
    BudgetDocumentDTO dto = new BudgetDocumentDTO()
            .setId(entity.getId())
            .setDocumentDate(entity.getDocumentDate())
            .setDocumentTitle(entity.getDocumentTitle())
            .setDocumentType(entity.getDocumentType())
            .setPaymentMethod(entity.getPaymentMethod())
            .setAccountId(entity.getAccount().getId())
            .setCurrency(entity.getCurrency())
            .setExchangeRate(entity.getCurrencyRate());
    return dto;
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
