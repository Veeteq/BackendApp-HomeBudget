package com.veeteq.finance.budget.repository;

import com.veeteq.finance.budget.model.BudgetDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetDocumentRepository extends JpaRepository<BudgetDocument, Long> {
}
