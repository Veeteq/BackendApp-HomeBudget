package com.veeteq.finance.budget.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "expenses", uniqueConstraints = @UniqueConstraint(name =  "expenses_pk", columnNames = {"expe_id"}))
@GenericGenerator(name = "expe_id_generator",
                  strategy = "com.veeteq.finance.budget.model.BudgetSequenceGenerator",
                  parameters = {@Parameter(name = "sequence_name", value = "expe_seq"), @Parameter(name = "allocationSize", value = "1")})
@AttributeOverride(name = "price",   column = @Column(name = "expe_pric_am"))
@AttributeOverride(name = "comment", column = @Column(name = "expe_comm_tx"))
public class Expense extends BudgetDocumentItem {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="expe_id_generator")
    @Column(name = "expe_id", nullable = false, unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "docu_id", referencedColumnName = "docu_id")
    private BudgetDocument document;

    @Column(name = "expe_item_cn")
    private BigDecimal count;

    public Long getId() {
        return id;
    }

    public Expense setId(Long id) {
        this.id = id;
        return this;
    }

    public BudgetDocument getDocument() {
        return document;
    }

    public Expense setDocument(BudgetDocument document) {
        this.document = document;
        return this;
    }

    public BigDecimal getCount() {
        return count;
    }

    public Expense setCount(BigDecimal count) {
        this.count = count;
        return this;
    }
}
