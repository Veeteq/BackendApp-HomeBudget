package com.veeteq.finance.budget.model;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import java.io.Serializable;

public class BudgetSequenceGenerator extends SequenceStyleGenerator {

  @Override
  public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {

    if(object instanceof NamedEntity) {
      NamedEntity persistent = (NamedEntity) object;
      if (persistent.getId() != null && persistent.getId() >= 0) {
        return persistent.getId();
      }
    }
    return super.generate(session, object);
  }
}
