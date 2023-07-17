package com.veeteq.finance.budget.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.veeteq.finance.budget.model.builder.NoteBuilder;

@Entity
@DiscriminatorValue("NOTE")
public final class Note extends BudgetDocument {
  private static final long serialVersionUID = 1L;

  @Column(name = "cprt_id")
  private Long counterpartyId;

  public Note() {}

  public Note(NoteBuilder builder) {
    super(builder);
    this.counterpartyId = builder.getCounterpartyId();
  }

  public static NoteBuilder builder() {
    return new NoteBuilder();
  }

}
