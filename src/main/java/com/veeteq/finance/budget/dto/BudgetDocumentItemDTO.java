package com.veeteq.finance.budget.dto;

import com.veeteq.finance.budget.model.DocumentType;

import java.math.BigDecimal;

public class BudgetDocumentItemDTO {

  private Long id;
  private Long itemId;
  private DocumentType type;
  private BigDecimal count;
  private BigDecimal price;
  private String comment;
}
