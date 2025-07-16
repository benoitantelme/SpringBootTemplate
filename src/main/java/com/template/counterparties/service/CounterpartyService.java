package com.template.counterparties.service;

import com.template.counterparties.model.Counterparty;
import com.template.counterparties.repository.CounterpartyRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CounterpartyService {

  @Autowired private CounterpartyRepository counterpartyRepository;

  public Counterparty getOrCreate(String counterpartyName) {
    return counterpartyRepository
        .findByName(counterpartyName)
        .orElse(new Counterparty(counterpartyName));
  }

  public void updateOrInsert(Counterparty counterparty) {
    Optional<Counterparty> existingCounterparty =
        counterpartyRepository.findByName(counterparty.getName());
    if (existingCounterparty.isPresent()) {
      // updates, none
      counterpartyRepository.save(counterparty);
    } else {
      counterpartyRepository.save(counterparty);
    }
  }

  public void deleteCounterparty(Counterparty counterparty) {
    Optional<Counterparty> existingCounterparty =
        counterpartyRepository.findByName(counterparty.getName());
    existingCounterparty.ifPresent(value -> counterpartyRepository.delete(value));
  }

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
