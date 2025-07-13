package com.template.counterparty.service;

import com.template.counterparty.model.Counterparty;
import com.template.counterparty.repository.CounterpartyRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CounterpartyService {

  @Autowired private CounterpartyRepository counterpartyRepository;

  public Counterparty postCounterparty(Counterparty counterparty) {
    return counterpartyRepository.save(counterparty);
  }

  public Optional<Counterparty> getCounterparty(String counterparty) {
    return counterpartyRepository.findByName(counterparty);
  }

  public List<Counterparty> getCOunterparties() {
    return counterpartyRepository.findAll();
  }
}
