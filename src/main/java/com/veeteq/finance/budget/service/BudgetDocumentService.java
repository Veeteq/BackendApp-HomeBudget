package com.veeteq.finance.budget.service;

import com.veeteq.finance.budget.dto.BudgetDocumentDTO;
import com.veeteq.finance.budget.model.BudgetDocument;

public interface BudgetDocumentService {
  BudgetDocument saveDocument(BudgetDocumentDTO document);
  void updateDocument(BudgetDocumentDTO document, Long id);
  void deleteDocument(Long id);
}
