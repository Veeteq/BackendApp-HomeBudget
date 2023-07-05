package com.veeteq.finance.budget.model;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PaymentMethod {
    CASH("Cash"),
    CREDIT_CARD("Credit card"),
    DEBIT_CARD("Debit card"),
    EFT("Money transfer"),
    ATM_WITHDRAWAL("ATM Withdrawal"),
    ATM_DEPOSIT("ATM Deposit");
    
    private final String value;

    private PaymentMethod(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
    
    @JsonCreator
    public PaymentMethod findByValue(String value) {
        return Stream.of(PaymentMethod.values())
        .filter(el -> el.value.equals(value))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException());
    }
}
