package com.veeteq.finance.budget.service;

import java.util.List;

import com.veeteq.finance.budget.dto.BudgetDocumentDTO;

public interface BudgetDocumentService {
  BudgetDocumentDTO saveDocument(BudgetDocumentDTO document);
  void updateDocument(BudgetDocumentDTO document, Long id);
  void deleteDocument(Long id);
  List<String> getDocumentTitlesWithPattern(String pattern);
}
