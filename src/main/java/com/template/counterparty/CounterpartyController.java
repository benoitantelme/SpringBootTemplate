package com.template.counterparty;

import com.template.counterparty.db.CounterpartyRepository;
import com.template.counterparty.model.Counterparty;
import com.template.trades.TradeService;
import com.template.trades.model.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CounterpartyController {

    @Autowired
    CounterpartyRepository repo;

    @GetMapping("/counterparties")
    public List<Counterparty> getCounterparties() {
        return repo.findAll();
    }

    @GetMapping("/counterparty/{name}")
    public ResponseEntity<Counterparty> getCounterparty(@PathVariable String name) {
        List<Counterparty> opTrade = repo.findByName(name);
        return opTrade.size() == 1 ? new ResponseEntity<>(opTrade.getFirst(), HttpStatus.OK) : ResponseEntity.noContent().build();
    }

}
