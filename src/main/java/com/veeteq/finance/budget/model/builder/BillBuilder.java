package com.veeteq.finance.budget.model.builder;

import com.veeteq.finance.budget.model.Bill;

public class BillBuilder extends BudgetDocumentBuilder<BillBuilder, Bill> {
    private Long counterpartyId;
    private Long bankStatementDetailId;

    public BillBuilder() {}

    public BillBuilder withCounterpartyId(Long counterpartyId) {
        this.counterpartyId = counterpartyId;
        return this;
    }

    public BillBuilder withBankStatementDetailId(Long bankStatementDetailId) {
        this.bankStatementDetailId = bankStatementDetailId;
        return this;
    }

    public Long getCounterpartyId() {
        return counterpartyId;
    }

    public Long getBankStatementDetailId() {
        return bankStatementDetailId;
    }

    @Override
    protected BillBuilder self() {
      return this;
    }

    @Override
    public Bill build() {
        return new Bill(this);
    }

  }