package com.veeteq.finance.budget.model.builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import com.veeteq.finance.budget.model.Account;
import com.veeteq.finance.budget.model.BudgetDocumentType;
import com.veeteq.finance.budget.model.PaymentMethod;

public abstract class BudgetDocumentBuilder<T, R> {
    private Long id;
    private LocalDate documentDate;
    private BudgetDocumentType documentType;
    private String documentTitle;
    private Account account;
    private PaymentMethod paymentMethod;
    private Currency currency;
    private BigDecimal currencyRate;

    public T withId(Long id) {
        this.id = id;
        return self();
    }

    public T withDocumentType(BudgetDocumentType documentType) {
        this.documentType = documentType;
        return self();
    }

    public T withDocumentDate(LocalDate documentDate) {
        this.documentDate = documentDate;
        return self();
    }

    public T withDocumentTitle(String documentTitle) {
        this.documentTitle = documentTitle;
        return self();
    }

    public T withAccount(Account account) {
        this.account = account;
        return self();
    }

    public T withPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
        return self();
    }

    public T withCurrency(Currency currency) {
        this.currency = currency;
        return self();
    }

    public T withCurrencyRate(BigDecimal currencyRate) {
        this.currencyRate = currencyRate;
        return self();
    }

    public LocalDate getDocumentDate() {
        return documentDate;
    }

    public BudgetDocumentType getDocumentType() {
        return documentType;
    }

    public String getDocumentTitle() {
        return documentTitle;
    }

    public Account getAccount() {
        return account;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getCurrencyRate() {
        return currencyRate;
    }

    public Long getId() {
        return this.id;
    }

    protected abstract T self();

    protected abstract R build();
}
