package com.veeteq.finance.budget.model;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DocumentType {

    TRANSFER("Transfer"),
    NOTE("Note"),
    BILL("Bill"),
    INVOICE("Invoice");
    
    private final String value;
    
    private DocumentType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public DocumentType findByValue(String value) {
        return Stream.of(DocumentType.values())
        .filter(el -> el.value.equals(value))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException());
    }
}
