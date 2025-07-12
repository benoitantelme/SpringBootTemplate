package com.template.counterparty.db;

import com.template.counterparty.model.Counterparty;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface CounterpartyRepository extends CrudRepository<Counterparty, Long> {

  List<Counterparty> findByName(String name);

  @Override
  List<Counterparty> findAll();
}
