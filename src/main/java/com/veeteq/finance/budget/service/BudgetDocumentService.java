package com.veeteq.finance.budget.service;

import com.veeteq.finance.budget.dto.BudgetDocumentDTO;

public interface BudgetDocumentService {
  void saveDocument(BudgetDocumentDTO document);
  void updateDocument(BudgetDocumentDTO document, Long id);
  void deleteDocument(Long id);
}
