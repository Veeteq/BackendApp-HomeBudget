package com.veeteq.finance.budget.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@MappedSuperclass
public abstract class BudgetDocumentItem extends BaseEntity<BudgetDocumentItem> {
  private static final long serialVersionUID = 1L;

  @Column(name = "oper_dt", nullable = false)
  protected LocalDate operationDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  protected Account account;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "item_id", nullable = false)
  protected Item item;

  protected BigDecimal price;

  protected String comment;
}
