package com.veeteq.finance.budget.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.veeteq.finance.budget.model.builder.InvoiceBuilder;

@Entity
@DiscriminatorValue("INVOICE")
public final class Invoice extends BudgetDocument {
  private static final long serialVersionUID = 1L;

  @Column(name = "cprt_id")
  private Long counterpartyId;

  @Column(name = "bank_stmt_deta_id")
  private Long bankStatementDetailId;

  @Column(name = "invo_tx")
  private String invoiceNumber;

  public Invoice() {
  }

  public Invoice(InvoiceBuilder builder) {
    super(builder);
    this.counterpartyId = builder.getCounterpartyId();
    this.bankStatementDetailId = builder.getBankStatementDetailId();
    this.invoiceNumber = builder.getInvoiceNumber();
  }

  public Long getCounterpartyId() {
    return counterpartyId;
  }

  public Long getBankStatementDetailId() {
    return bankStatementDetailId;
  }

  public String getInvoiceNumber() {
    return invoiceNumber;
  }

  public static InvoiceBuilder builder() {
    return new InvoiceBuilder();
  }

}
