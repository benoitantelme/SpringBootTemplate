package com.template.books.controller;

import com.template.books.model.Counterparty;
import com.template.books.service.CounterpartyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CounterpartyController {

  @Autowired CounterpartyService counterpartyService;

  @GetMapping("/counterparties")
  public List<Counterparty> getCounterparties() {
    return counterpartyService.findCounterparties();
  }

  @GetMapping("/counterparty/{name}")
  public ResponseEntity<Counterparty> getCounterparty(@PathVariable String name) {
    return counterpartyService
        .findCounterparty(name)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.noContent().build());
  }

  @PostMapping(value = "/counterparty", consumes = "application/json")
  public ResponseEntity<Counterparty> postCounterparty(@RequestBody Counterparty counterparty) {
    Counterparty returnedCounterparty = counterpartyService.saveCounterparty(counterparty);
    return new ResponseEntity<>(returnedCounterparty, HttpStatus.CREATED);
  }
}
