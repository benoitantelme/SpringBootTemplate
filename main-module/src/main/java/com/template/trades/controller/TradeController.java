package com.template.trades.controller;

import com.template.trades.model.Trade;
import com.template.trades.service.TradeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TradeController {

  @Autowired TradeService tradeService;

  @GetMapping("/trades")
  public List<Trade> getTrades() {
    return tradeService.findTrades();
  }

  @GetMapping("/trade/{name}")
  public ResponseEntity<Trade> getTrade(@PathVariable String name) {
    return tradeService
        .findTrade(name)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.noContent().build());
  }

  @PostMapping(value = "/trade", consumes = "application/json")
  public ResponseEntity<Trade> postCounterparty(@RequestBody Trade trade) {
    Trade returnedTrade = tradeService.saveTrade(trade);
    return new ResponseEntity<>(returnedTrade, HttpStatus.CREATED);
  }
}
