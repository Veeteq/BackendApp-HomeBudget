package com.veeteq.finance.budget.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.veeteq.finance.budget.model.DocumentType;
import com.veeteq.finance.budget.model.PaymentMethod;

public class BudgetDocumentDTO {
    private Long id;
    
    @NotNull
    private DocumentType documentType;
    
    @NotNull
    private LocalDate documentDate;
    
    @NotEmpty
    private String documentTitle;

    @NotNull
    private PaymentMethod paymentMethod;

    @NotNull
    private Long accountId;

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

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
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

}
