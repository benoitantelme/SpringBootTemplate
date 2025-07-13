package com.template.counterparty.db;

import com.template.counterparty.model.Counterparty;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface CounterpartyRepository extends CrudRepository<Counterparty, Long> {

  Optional<Counterparty> findByName(String name);

  @Override
  List<Counterparty> findAll();
}
