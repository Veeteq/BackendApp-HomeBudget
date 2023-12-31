package com.veeteq.finance.budget.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Table(name = "incomes", uniqueConstraints = @UniqueConstraint(columnNames = "inco_id"))
@GenericGenerator(name = "inco_seq_generator",
                  strategy = "com.veeteq.finance.budget.model.BudgetSequenceGenerator",
                  parameters = {@Parameter(name = "sequence_name", value = "inco_seq")})
@AttributeOverride(name = "count",   column = @Column(name = "inco_item_cn"))
@AttributeOverride(name = "price",   column = @Column(name = "inco_pric_am"))
@AttributeOverride(name = "comment", column = @Column(name = "inco_comm_tx"))
public class Income extends BudgetDocumentItem {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="inco_seq_generator")
    @Column(name = "inco_id", nullable = false, unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "docu_id", referencedColumnName = "docu_id")
    private BudgetDocument document;

    public Long getId() {
        return id;
    }

    public Income setId(Long id) {
        this.id = id;
        return this;
    }

    public BudgetDocument getDocument() {
        return document;
    }

    public Income setDocument(BudgetDocument document) {
        this.document = document;
        return this;
    }
}
