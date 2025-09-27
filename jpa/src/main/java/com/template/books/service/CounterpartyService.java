package com.template.books.service;

import com.template.books.model.Counterparty;
import com.template.books.repository.CounterpartyRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CounterpartyService {

  @Autowired private CounterpartyRepository counterpartyRepository;

  public Counterparty saveCounterparty(Counterparty counterparty) {
    return counterpartyRepository.save(counterparty);
  }

  public Optional<Counterparty> findCounterparty(String counterparty) {
    return counterpartyRepository.findByName(counterparty);
  }

  public List<Counterparty> findCounterparties() {
    return counterpartyRepository.findAll();
  }
}
