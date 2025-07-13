package com.template.counterparty;

import com.template.counterparty.db.CounterpartyRepository;
import com.template.counterparty.model.Counterparty;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CounterpartyController {

  @Autowired CounterpartyRepository counterpartyRepository;

  @GetMapping("/counterparties")
  public List<Counterparty> getCounterparties() {
    return counterpartyRepository.findAll();
  }

  @GetMapping("/counterparty/{name}")
  public ResponseEntity<Counterparty> getCounterparty(@PathVariable String name) {
    return counterpartyRepository.findByName(name)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.noContent().build());
  }
}
