package com.template.trades.model;

import jakarta.persistence.*;

@Entity
public class Trade {

  @Id private String name;

  private String counterparty;

  private Double amount;

  private Currency currency;

  public Trade(String name, String counterparty, double amount, Currency currency) {
    this.name = name;
    this.counterparty = counterparty;
    this.amount = amount;
    this.currency = currency;
  }

  public Trade() {}

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCounterparty() {
    return counterparty;
  }

  public void setCounterparty(String counterparty) {
    this.counterparty = counterparty;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public Currency getCurrency() {
    return currency;
  }

  public void setCurrency(Currency currency) {
    this.currency = currency;
  }
}
