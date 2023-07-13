package com.veeteq.finance.budget.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "expenses", uniqueConstraints = @UniqueConstraint(name =  "expenses_pk", columnNames = {"expe_id"}))

@AttributeOverride(name = "price", column = @Column(name = "expe_pric_am"))
@AttributeOverride(name = "comment", column = @Column(name = "expe_comm_tx"))
public class Expense extends BudgetDocumentItem {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "expe_id", nullable = false, unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "docu_id", referencedColumnName = "docu_id")
    private BudgetDocument document;

    @Column(name = "expe_item_cn")
    private BigDecimal count;
}
