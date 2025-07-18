package com.template.counterparties.model;

import jakarta.persistence.*;

@Entity
@Table(name = "counterparty")
public class Counterparty {

  @Id private String name;

  public Counterparty() {}

  public Counterparty(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
