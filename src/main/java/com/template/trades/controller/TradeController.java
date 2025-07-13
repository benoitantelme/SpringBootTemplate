package com.template.trades.controller;

import com.template.trades.model.Trade;
import com.template.trades.service.TradeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TradeController {

  @Autowired TradeService tradeService;

  @GetMapping("/trades")
  public List<Trade> getTrades() {
    return tradeService.getTrades();
  }

  @GetMapping("/trade/{name}")
  public ResponseEntity<Trade> getTrade(@PathVariable String name) {
    return tradeService
        .getTrade(name)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.noContent().build());
  }
}
