package com.veeteq.finance.budget.repository;

import com.veeteq.finance.budget.model.BudgetDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BudgetDocumentRepository extends JpaRepository<BudgetDocument, Long> {

  @Query(value = "SELECT DISTINCT bd.documentTitle from BudgetDocument bd WHERE lower(bd.documentTitle) LIKE LOWER(CONCAT('%', :pattern, '%')) ORDER BY bd.documentTitle",
         nativeQuery = false)
  List<String> findDistinctByDocumentTitleContainingIgnoreCase(@Param(value = "pattern") String pattern);
}
