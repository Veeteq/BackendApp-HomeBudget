package com.veeteq.finance.budget.model;

import javax.persistence.*;

//@MappedSuperclass
//@Table(name = "document_items", uniqueConstraints = @UniqueConstraint(columnNames = "ditm_id"))
public class BudgetDocumentItem extends BaseEntity<BudgetDocumentItem> {
  private static final long serialVersionUID = 1L;
  
  @Id
  @Column(name = "ditm_id")
  private Long id;
    
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "docu_id", referencedColumnName = "docu_id")
  private BudgetDocument document;
}
