package com.veeteq.finance.budget.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("INVOICE")
public final class Invoice extends BudgetDocument<Invoice> {
  private static final long serialVersionUID = 1L;

  @Column(name = "cprt_id")
  private Long counterpartyId;

  @Column(name = "invo_tx")
  private String invoiceNumber;

  public Invoice() {}

  public Invoice(Builder builder) {
    super(builder);
    this.counterpartyId = builder.counterpartyId;
    this.invoiceNumber = builder.invoiceNumber;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder extends BudgetDocument.Builder<Builder> {
    private Long counterpartyId;
    private String invoiceNumber;

    private Builder() {}

    public Builder setCounterpartyId(Long counterpartyId) {
      this.counterpartyId = counterpartyId;
      return self();
    }

    public Builder setInvoiceNumber(String invoiceNumber) {
      this.invoiceNumber = invoiceNumber;
      return self();
    }

    @Override
    public Invoice build() {
      return new Invoice(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
