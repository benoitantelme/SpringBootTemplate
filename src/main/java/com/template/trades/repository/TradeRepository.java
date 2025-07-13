package com.template.trades.repository;

import com.template.trades.model.Trade;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface TradeRepository extends CrudRepository<Trade, Long> {

  Optional<Trade> findByName(String name);

  @Override
  List<Trade> findAll();
}
