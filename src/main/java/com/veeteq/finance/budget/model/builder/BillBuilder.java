package com.veeteq.finance.budget.model.builder;

import com.veeteq.finance.budget.model.Bill;

public class BillBuilder extends BudgetDocumentBuilder<BillBuilder, Bill> {

    public BillBuilder() {}

    @Override
    protected BillBuilder self() {
      return this;
    }

    @Override
    public Bill build() {
        return new Bill(this);
    }

  }