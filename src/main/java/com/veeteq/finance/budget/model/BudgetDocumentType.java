package com.veeteq.finance.budget.model;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum BudgetDocumentType {

    TRANSFER("Transfer"),
    NOTE("Note"),
    BILL("Bill"),
    INVOICE("Invoice");
    
    private final String value;
    
    private BudgetDocumentType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public BudgetDocumentType findByValue(String value) {
        return Stream.of(BudgetDocumentType.values())
        .filter(el -> el.value.equals(value))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException());
    }
}
