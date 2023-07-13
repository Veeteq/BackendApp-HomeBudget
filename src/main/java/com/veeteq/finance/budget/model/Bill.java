package com.veeteq.finance.budget.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.veeteq.finance.budget.model.builder.BillBuilder;

@Entity
@DiscriminatorValue("BILL")
public final class Bill extends BudgetDocument<Bill> {
  private static final long serialVersionUID = 1L;

  public Bill() {}

  public Bill(BillBuilder builder) {
      super(builder);
  }

  public static BillBuilder builder() {
    return new BillBuilder();
  }

}
