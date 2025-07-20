package com.template.trades.model;

import com.template.counterparties.model.Counterparty;
import jakarta.persistence.*;

@Entity
@Table(name = "trade")
public class Trade {

  @Id private String name;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
  private Counterparty counterparty;

  private Double amount;

  private Currency currency;

  public Trade(String name, Counterparty counterparty, double amount, Currency currency) {
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

  public Counterparty getCounterparty() {
    return counterparty;
  }

  public void setCounterparty(Counterparty counterparty) {
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
