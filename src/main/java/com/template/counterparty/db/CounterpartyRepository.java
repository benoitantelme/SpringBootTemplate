package com.template.counterparty.db;

import com.template.counterparty.model.Counterparty;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CounterpartyRepository extends CrudRepository<Counterparty, Long> {

    List<Counterparty> findByName(String name);

    @Override
    List<Counterparty> findAll();

}