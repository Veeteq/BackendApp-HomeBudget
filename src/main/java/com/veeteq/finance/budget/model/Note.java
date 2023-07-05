package com.veeteq.finance.budget.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("NOTE")
public final class Note extends BudgetDocument<Note> {
  private static final long serialVersionUID = 1L;

  @Column(name = "cprt_id")
  private Long counterpartyId;

  public Note() {}

  public Note(Builder builder) {
    super(builder);
    this.counterpartyId = builder.counterpartyId;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder extends BudgetDocument.Builder<Builder> {
    private Long counterpartyId;

    private Builder() {}

    public Builder setCounterpartyId(Long counterpartyId) {
      this.counterpartyId = counterpartyId;
      return self();
    }

    @Override
    public Note build() {
      return new Note(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
