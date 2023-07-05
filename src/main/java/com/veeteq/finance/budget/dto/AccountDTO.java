package com.veeteq.finance.budget.dto;

public class AccountDTO {

    private Long id;
    private String displayName;

    public Long getId() {
        return id;
    }

    public AccountDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public AccountDTO setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

}
