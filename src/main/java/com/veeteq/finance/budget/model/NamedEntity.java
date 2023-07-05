package com.veeteq.finance.budget.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class NamedEntity<T> implements Comparable<NamedEntity<T>>, Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="default_seq")
  private Long id;

  private String name;

  protected Long getId() {
    return id;
  }

  protected NamedEntity<T> setId(Long id) {
    this.id = id;
    return this;
  }

  protected String getName() {
    return this.name;
  }

  protected NamedEntity<T> setName(String name) {
    this.name = name;
    return this;
  }

  @Override
  public int compareTo(NamedEntity<T> other) {
    if(other != null) {
      return this.getId().compareTo(other.getId());
    }
    return -1;
  }

}
