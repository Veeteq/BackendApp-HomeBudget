package com.veeteq.finance.budget.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.veeteq.finance.budget.model.builder.BillBuilder;

@Entity
@DiscriminatorValue("BILL")
public final class Bill extends BudgetDocument {
  private static final long serialVersionUID = 1L;

  @Column(name = "cprt_id")
  private Long counterpartyId;

  @Column(name = "bank_stmt_deta_id")
  private Long bankStatementDetailId;

  public Bill() {}

  public Long getCounterpartyId() {
    return counterpartyId;
  }

  public Long getBankStatementDetailId() {
    return bankStatementDetailId;
  }

  public Bill(BillBuilder builder) {
      super(builder);
      this.counterpartyId = builder.getCounterpartyId();
      this.bankStatementDetailId = builder.getBankStatementDetailId();
  }

  public static BillBuilder builder() {
    return new BillBuilder();
  }

}
