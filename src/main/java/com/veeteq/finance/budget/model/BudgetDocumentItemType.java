package com.veeteq.finance.budget.model;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum BudgetDocumentItemType {
  EXPENSE("Expense"),
  INCOME("Income");

  private final String description;

  private BudgetDocumentItemType(String description) {
    this.description = description;
  }

  @JsonValue
  public String getDescription() {
    return description;
  }

  @JsonCreator
  public BudgetDocumentItemType findByValue(String value) {
    return Stream.of(BudgetDocumentItemType.values())
            .filter(el -> el.description.equals(value))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException());
  }
}
