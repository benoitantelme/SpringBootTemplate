package com.template.counterparties.controller;

import com.template.counterparties.model.Counterparty;
import com.template.counterparties.service.CounterpartyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CounterpartyController {

  @Autowired CounterpartyService counterpartyService;

  @GetMapping("/counterparties")
  public List<Counterparty> getCounterparties() {
    return counterpartyService.getCounterparties();
  }

  @GetMapping("/counterparty/{name}")
  public ResponseEntity<Counterparty> getCounterparty(@PathVariable String name) {
    return counterpartyService
        .getCounterparty(name)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.noContent().build());
  }
}
