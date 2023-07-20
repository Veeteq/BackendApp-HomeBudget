package com.veeteq.finance.budget.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.LinkedList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.veeteq.finance.budget.model.BudgetDocumentType;
import com.veeteq.finance.budget.model.PaymentMethod;

public class BudgetDocumentDTO {
    private Long id;
    
    @NotNull(message = "Document type is required")
    private BudgetDocumentType documentType;
    
    @NotNull(message = "Document date is required")
    private LocalDate documentDate;
    
    @NotNull(message = "Document title is required")
    private String documentTitle;

    @NotNull(message = "Payment method is required")
    private PaymentMethod paymentMethod;

    @NotNull(message = "Account is required")
    private Long accountId;

    @NotEmpty(message = "At least one item is required")
    private List<BudgetDocumentItemDTO> items = new LinkedList<>();

    @JsonProperty(value = "bankStatementId")
    private Long bankStatementDetailId;

    @JsonProperty(value = "counterparty")
    private CounterpartyInfoDTO counterpartyInfo;

    private String invoiceNumber;

    private Long counterpartyId;
    
    private Currency currency;
    
    private BigDecimal exchangeRate;

    public Long getId() {
        return id;
    }

    public BudgetDocumentDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public BudgetDocumentType getDocumentType() {
        return documentType;
    }

    public BudgetDocumentDTO setDocumentType(BudgetDocumentType documentType) {
        this.documentType = documentType;
        return this;
    }

    public LocalDate getDocumentDate() {
        return documentDate;
    }

    public BudgetDocumentDTO setDocumentDate(LocalDate documentDate) {
        this.documentDate = documentDate;
        return this;
    }

    public String getDocumentTitle() {
        return documentTitle;
    }

    public BudgetDocumentDTO setDocumentTitle(String documentTitle) {
        this.documentTitle = documentTitle;
        return this;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public BudgetDocumentDTO setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public BudgetDocumentDTO setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
        return this;
    }

    public Long getAccountId() {
        return accountId;
    }

    public BudgetDocumentDTO setAccountId(Long accountId) {
        this.accountId = accountId;
        return this;
    }

    public Long getCounterpartyId() {
        return counterpartyId;
    }

    public BudgetDocumentDTO setCounterpartyId(Long counterpartyId) {
        this.counterpartyId = counterpartyId;
        return this;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BudgetDocumentDTO setCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public BudgetDocumentDTO setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
        return this;
    }

    public List<BudgetDocumentItemDTO> getItems() {
        return items;
    }

    public BudgetDocumentDTO setItems(List<BudgetDocumentItemDTO> items) {
        this.items = items;
        return this;
    }

    public void addToItems(BudgetDocumentItemDTO item) {
        items.add(item);
    }

    public Long getBankStatementDetailId() {
        return bankStatementDetailId;
    }

    public BudgetDocumentDTO setBankStatementDetailId(Long bankStatementDetailId) {
        this.bankStatementDetailId = bankStatementDetailId;
        return this;
    }

    public CounterpartyInfoDTO getCounterpartyInfo() {
        return counterpartyInfo;
    }

    public BudgetDocumentDTO setCounterpartyInfo(CounterpartyInfoDTO counterpartyInfo) {
        this.counterpartyInfo = counterpartyInfo;
        return this;
    }
}
