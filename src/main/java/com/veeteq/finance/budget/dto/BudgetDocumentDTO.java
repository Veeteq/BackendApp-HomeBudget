package com.veeteq.finance.budget.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.LinkedList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

    private Long bankStatementId;


    private String invoiceNumber;

    private Long counterpartyId;
    
    private Currency currency;
    
    private BigDecimal exchangeRate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BudgetDocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(BudgetDocumentType documentType) {
        this.documentType = documentType;
    }

    public LocalDate getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(LocalDate documentDate) {
        this.documentDate = documentDate;
    }

    public String getDocumentTitle() {
        return documentTitle;
    }

    public void setDocumentTitle(String documentTitle) {
        this.documentTitle = documentTitle;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getCounterpartyId() {
        return counterpartyId;
    }

    public void setCounterpartyId(Long counterpartyId) {
        this.counterpartyId = counterpartyId;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
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

    public Long getBankStatementId() {
        return bankStatementId;
    }

    public BudgetDocumentDTO setBankStatementId(Long bankStatementId) {
        this.bankStatementId = bankStatementId;
        return this;
    }
}
