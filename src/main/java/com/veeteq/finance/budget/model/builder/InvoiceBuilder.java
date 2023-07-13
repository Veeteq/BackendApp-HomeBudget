package com.veeteq.finance.budget.model.builder;

import com.veeteq.finance.budget.model.Invoice;

public class InvoiceBuilder extends BudgetDocumentBuilder<InvoiceBuilder, Invoice> {
    private Long counterpartyId;
    private String invoiceNumber;

    public InvoiceBuilder() {}

    @Override
    protected InvoiceBuilder self() {
      return this;
    }

    public InvoiceBuilder withCounterpartyId(Long counterpartyId) {
      this.counterpartyId = counterpartyId;
      return this;
    }

    public InvoiceBuilder withInvoiceNumber(String invoiceNumber) {
      this.invoiceNumber = invoiceNumber;
      return this;
    }

    public Long getCounterpartyId() {
        return counterpartyId;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    @Override
    public Invoice build() {
        return new Invoice(this);
    }

  }