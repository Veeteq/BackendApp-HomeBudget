package com.veeteq.finance.budget.model.builder;

import com.veeteq.finance.budget.model.Note;

public class NoteBuilder extends BudgetDocumentBuilder<NoteBuilder, Note> {
    private Long counterpartyId;

    public NoteBuilder() {}

    @Override
    protected NoteBuilder self() {
      return this;
    }

    public NoteBuilder withCounterpartyId(Long counterpartyId) {
      this.counterpartyId = counterpartyId;
      return this;
    }

    public Long getCounterpartyId() {
        return counterpartyId;
    }

    @Override
    public Note build() {
        return new Note(this);
    }

  }