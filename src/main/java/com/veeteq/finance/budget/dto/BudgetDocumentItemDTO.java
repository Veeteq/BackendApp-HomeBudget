package com.veeteq.finance.budget.dto;

import com.veeteq.finance.budget.model.BudgetDocumentItemType;

import java.math.BigDecimal;

public class BudgetDocumentItemDTO {

  private Long id;
  private ItemDTO product;
  private BudgetDocumentItemType type;
  private BigDecimal count;
  private BigDecimal price;
  private String comment;

  public Long getId() {
    return id;
  }

  public BudgetDocumentItemDTO setId(Long id) {
    this.id = id;
    return this;
  }

  public ItemDTO getProduct() {
    return product;
  }

  public BudgetDocumentItemDTO setProduct(ItemDTO product) {
    this.product = product;
    return this;
  }

  public BudgetDocumentItemType getType() {
    return type;
  }

  public BudgetDocumentItemDTO setType(BudgetDocumentItemType type) {
    this.type = type;
    return this;
  }

  public BigDecimal getCount() {
    return count;
  }

  public BudgetDocumentItemDTO setCount(BigDecimal count) {
    this.count = count;
    return this;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public BudgetDocumentItemDTO setPrice(BigDecimal price) {
    this.price = price;
    return this;
  }

  public String getComment() {
    return comment;
  }

  public BudgetDocumentItemDTO setComment(String comment) {
    this.comment = comment;
    return this;
  }
}
