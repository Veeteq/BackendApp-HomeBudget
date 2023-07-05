package com.veeteq.finance.budget.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Table(name = "items")
@AttributeOverride(name = "id", column = @Column(name = "item_id"))
@AttributeOverride(name = "name", column = @Column(name = "item_name_tx"))
@GenericGenerator(name = "default_seq",
                  strategy = "com.veeteq.finance.budget.model.BudgetSequenceGenerator",
                  parameters = {@Parameter(name="sequence_name", value="item_seq")})
@NamedEntityGraph(name = "item-category", attributeNodes = @NamedAttributeNode("category"))
public class Item extends NamedEntity<Item> {
  private static final long serialVersionUID = 1L;
    
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name="cate_id", nullable=false)
  private Category category;

  @Override
  public Long getId() {
    return super.getId();
  }

  @Override
  protected Item setId(Long id) {
    super.setId(id);
    return this;
  }

  @Override
  public String getName() {
    return super.getName();
  }

  @Override
  protected Item setName(String name) {
    super.setName(name);
    return this;
  }

  public Category getCategory() {
    return category;
  }

  protected Item setCategory(Category category) {
    this.category = category;
    return this;
  }

  @Override
  public String toString() {
    return "Item [name=" + super.getName() + ", id=" + super.getId() + ", category=" + category + "]";
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private Long id;
    private String name;
    private Category category;

    public Builder id(Long id) {
      this.id = id;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder category(Category category) {
      this.category = category;
      return this;
    }

    public Item build() {
      return new Item()
              .setId(this.id)
              .setName(this.name)
              .setCategory(this.category);
    }
  }

}
