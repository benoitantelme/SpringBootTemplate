package com.template.trades.db;

import com.template.trades.model.Trade;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TradeRepository extends CrudRepository<Trade, Long> {

  Optional<Trade> findByName(String name);

  @Override
  List<Trade> findAll();
}
