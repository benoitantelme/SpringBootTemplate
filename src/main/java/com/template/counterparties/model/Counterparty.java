package com.template.counterparties.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Counterparty {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  public Counterparty() {}

  public Counterparty(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public Long getId() {
    return id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
