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

  public LocalDate getOperationDate() {
    return operationDate;
  }

  public BudgetDocumentItem setOperationDate(LocalDate operationDate) {
    this.operationDate = operationDate;
    return this;
  }

  public Account getAccount() {
    return account;
  }

  public BudgetDocumentItem setAccount(Account account) {
    this.account = account;
    return this;
  }

  public Item getItem() {
    return item;
  }

  public BudgetDocumentItem setItem(Item item) {
    this.item = item;
    return this;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public BudgetDocumentItem setPrice(BigDecimal price) {
    this.price = price;
    return this;
  }

  public String getComment() {
    return comment;
  }

  public BudgetDocumentItem setComment(String comment) {
    this.comment = comment;
    return this;
  }

}
