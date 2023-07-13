package com.veeteq.finance.budget.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "users")
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
@AttributeOverride(name = "name", column = @Column(name = "user_name_tx"))
@GenericGenerator(name = "default_seq",
                  strategy = "com.veeteq.finance.budget.model.BudgetSequenceGenerator",
                  parameters = {@org.hibernate.annotations.Parameter(name="sequence_name", value="user_seq")})
public class Account extends NamedEntity<Account>{
    private static final long serialVersionUID = 1L;

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    protected Account setId(Long id) {
        super.setId(id);
        return this;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public Account setName(String name) {
        super.setName(name);
        return this;
    }

    @Override
    public String toString() {
        return "User [name=" + super.getName() + ", id=" + super.getId() + "]";
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String name;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Account build() {
            return new Account()
                    .setId(this.id)
                    .setName(this.name);
        }
    }
}
