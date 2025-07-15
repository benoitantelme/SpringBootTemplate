package com.template.counterparties.model;

import com.template.trades.model.Trade;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "counterparty")
public class Counterparty {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  @OneToMany(mappedBy = "counterparty", cascade = CascadeType.ALL)
  private List<Trade> trades;

  public Counterparty() {}

  public Counterparty(String name) {
    this.name = name;
    this.trades = new ArrayList<>();
  }

  public Counterparty(String name, List<Trade> trades) {
    this.name = name;
    this.trades = trades;
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

  public List<Trade> getTrades() {
    return trades;
  }

  public void setTrades(List<Trade> trades) {
    this.trades = trades;
  }
}
