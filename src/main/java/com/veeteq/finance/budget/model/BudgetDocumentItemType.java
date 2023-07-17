package com.veeteq.finance.budget.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

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
