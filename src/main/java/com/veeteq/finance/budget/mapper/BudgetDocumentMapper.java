package com.veeteq.finance.budget.mapper;

import com.veeteq.finance.budget.dto.BudgetDocumentDTO;
import com.veeteq.finance.budget.model.*;
import com.veeteq.finance.budget.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BudgetDocumentMapper<T> {

  private final AccountRepository accountRepository;

  @Autowired
  public BudgetDocumentMapper(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
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

    return entity;
  }

  private Bill createBill(BudgetDocumentDTO dto) {
    Long accountId = dto.getAccountId();
    Account account = accountRepository.getReferenceById(accountId);

    Bill bill = Bill.builder()
            .withId(dto.getId())
            .withDocumentDate(dto.getDocumentDate())
            .withAccount(account)
            .withPaymentMethod(dto.getPaymentMethod())
            .withCurrency(dto.getCurrency())
            .withCurrencyRate(dto.getExchangeRate())
            .build();
    return bill;
  }

  private Note createNote(BudgetDocumentDTO dto) {
    Long accountId = dto.getAccountId();
    Account account = accountRepository.getReferenceById(accountId);

    Note note = Note.builder()
            .withId(dto.getId())
            .withDocumentDate(dto.getDocumentDate())
            .withAccount(account)
            .withPaymentMethod(dto.getPaymentMethod())
            .withCurrency(dto.getCurrency())
            .withCurrencyRate(dto.getExchangeRate())
            .setCounterpartyId(dto.getCounterpartyId())
            .build();

    return note;
  }

  private Invoice createInvoice(BudgetDocumentDTO dto) {
    Long accountId = dto.getAccountId();
    Account account = accountRepository.getReferenceById(accountId);

    Invoice invoice = Invoice.builder()
            .withId(dto.getId())
            .withDocumentDate(dto.getDocumentDate())
            .withAccount(account)
            .withPaymentMethod(dto.getPaymentMethod())
            .withCurrency(dto.getCurrency())
            .withCurrencyRate(dto.getExchangeRate())
            .setCounterpartyId(dto.getCounterpartyId())
            .setInvoiceNumber(dto.getInvoiceNumber())
            .build();
    return invoice;
  }

  private BudgetDocument createTransfer(BudgetDocumentDTO dto) {
    return null;
  }

}
