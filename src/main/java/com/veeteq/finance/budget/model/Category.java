package com.veeteq.finance.budget.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="categories")
@AttributeOverride(name = "id", column = @Column(name = "cate_id"))
@AttributeOverride(name = "name", column = @Column(name = "cate_name_tx"))
@GenericGenerator(name = "default_seq",
                  strategy = "com.veeteq.finance.budget.model.BudgetSequenceGenerator",
                  parameters = {@Parameter(name="sequence_name", value="cate_seq")})
public class Category extends NamedEntity<Category>  {
    private static final long serialVersionUID = 1L;

    @Column(name = "cate_type_tx")
    @Enumerated(value = EnumType.STRING)
    private CategoryType categoryType;

    @OneToMany(mappedBy = "category")
    private Set<Item> items;

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    protected Category setId(Long id) {
        super.setId(id);
        return this;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    protected Category setName(String name) {
        super.setName(name);
        return this;
    }

    protected Category setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
        return this;
    }

    @Override
    public String toString() {
        return "Category [categoryType=" + categoryType + ", name=" + super.getName() + ", id=" + super.getId() + "]";
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String name;
        private CategoryType categoryType;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder categoryType(CategoryType categoryType) {
            this.categoryType = categoryType;
            return this;
        }

        public Category build() {
            return new Category()
                    .setId(this.id)
                    .setName(this.name)
                    .setCategoryType(this.categoryType);
        }
    }
}
