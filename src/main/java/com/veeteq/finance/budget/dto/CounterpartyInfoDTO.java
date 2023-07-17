package com.veeteq.finance.budget.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class CounterpartyInfoDTO {
  private Long id;
  private String fullname;
  private String shortname;

  public Long getId() {
    return id;
  }

  public CounterpartyInfoDTO setId(Long id) {
    this.id = id;
    return this;
  }

  public String getFullname() {
    return fullname;
  }

  public CounterpartyInfoDTO setFullname(String fullname) {
    this.fullname = fullname;
    return this;
  }

  public String getShortname() {
    return shortname;
  }

  public CounterpartyInfoDTO setShortname(String shortname) {
    this.shortname = shortname;
    return this;
  }
}
