package com.veeteq.finance.budget.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("BILL")
public final class Bill extends BudgetDocument<Bill> {
  private static final long serialVersionUID = 1L;

  public Bill() {}

  public Bill(Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder extends BudgetDocument.Builder<Builder> {

    private Builder() {}

    @Override
    public Bill build() {
      return new Bill(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
