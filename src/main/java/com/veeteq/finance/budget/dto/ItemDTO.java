package com.veeteq.finance.budget.dto;

public class ItemDTO {
  private Long id;
  private String name;
  private String categoryName;

  public Long getId() {
    return id;
  }

  public ItemDTO setId(Long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public ItemDTO setName(String name) {
    this.name = name;
    return this;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public ItemDTO setCategoryName(String categoryName) {
    this.categoryName = categoryName;
    return this;
  }
}
