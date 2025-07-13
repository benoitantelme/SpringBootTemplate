package com.template.counterparties.service;

import com.template.counterparties.model.Counterparty;
import com.template.counterparties.repository.CounterpartyRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CounterpartyService {

  @Autowired private CounterpartyRepository tradeRepository;

  public Counterparty saveCounterparty(Counterparty counterparty) {
    return tradeRepository.save(counterparty);
  }

  public Optional<Counterparty> findCounterparty(String counterparty) {
    return tradeRepository.findByName(counterparty);
  }

  public List<Counterparty> findCounterparties() {
    return tradeRepository.findAll();
  }
}
