package com.veeteq.finance.budget.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;

import com.veeteq.finance.budget.model.builder.BudgetDocumentBuilder;

@Entity
@Table(name = "documents", uniqueConstraints = @UniqueConstraint(columnNames = "docu_id"))
@SequenceGenerator(name = "seq_id", sequenceName = "docu_seq", allocationSize = 1)
@GenericGenerator(name = "default_seq",
                  strategy = "com.veeteq.finance.budget.model.BudgetSequenceGenerator",
                  parameters = {@Parameter(name = "sequence_name", value = "docu_seq") })
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING,
                     name = "docu_type_tx")
public abstract class BudgetDocument extends BaseEntity<BudgetDocument> {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="default_seq")
  @Column(name = "docu_id")
  private Long id;

  @Column(name = "docu_dt")
  @DateTimeFormat(pattern = "yyyy-mm-dd")
  private LocalDate documentDate;

  @Enumerated(EnumType.STRING)
  @Column(name="docu_type_tx", insertable = false, updatable = false)
  private BudgetDocumentType documentType;

  @Column(name = "docu_name_tx")
  private String documentTitle;

  @ManyToOne
  @JoinColumn(name = "acco_id")
  private Account account;

  @Enumerated(EnumType.STRING)
  @Column(name = "paym_meth_tx")
  private PaymentMethod paymentMethod;

  @Column(name = "curr_cd")
  private Currency currency;

  @Column(name = "curr_rate_am", columnDefinition = "number(10, 6) default 1 not null", nullable = false)
  private BigDecimal currencyRate = BigDecimal.ONE;

  @OneToMany(mappedBy = "document", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  private List<Expense> expenses = new LinkedList<>();

  @OneToMany(mappedBy = "document", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  private List<Income> incomes = new LinkedList<>();

  public BudgetDocument() {}

  public void addToDocumentItems(BudgetDocumentItem item) {
    if (item instanceof Expense expense) {
      System.out.println("adding expense");
      expense.setDocument(this);
      expenses.add(expense);
    }
    if (item instanceof Income income) {
      System.out.println("adding income");
      income.setDocument(this);
      incomes.add(income);
    }
  }

  public Long getId() {
    return id;
  }

  public LocalDate getDocumentDate() {
    return documentDate;
  }

  public BudgetDocumentType getDocumentType() {
    return documentType;
  }

  public String getDocumentTitle() {
    return documentTitle;
  }

  public Account getAccount() {
    return account;
  }

  public PaymentMethod getPaymentMethod() {
    return paymentMethod;
  }

  public Currency getCurrency() {
    return currency;
  }

  public BigDecimal getCurrencyRate() {
    return currencyRate;
  }

  public List<Expense> getExpenses() {
    return expenses;
  }

  public List<Income> getIncomes() {
    return incomes;
  }

  protected BudgetDocument(BudgetDocumentBuilder<?,?> builder) {
      this.id = builder.getId();
      this.documentDate = builder.getDocumentDate();
      this.documentType = builder.getDocumentType();
      this.documentTitle = builder.getDocumentTitle();
      this.account = builder.getAccount();
      this.paymentMethod = builder.getPaymentMethod();
      this.currency = builder.getCurrency();
      this.currencyRate = builder.getCurrencyRate();
  }

}
