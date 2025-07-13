package com.template.trades;

import com.template.trades.model.Trade;
import com.template.trades.repository.TradeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TradeController {

  @Autowired TradeRepository tradeRepository;

  @GetMapping("/trades")
  public List<Trade> getTrades() {
    return tradeRepository.findAll();
  }

  @GetMapping("/trade/{name}")
  public ResponseEntity<Trade> getTrade(@PathVariable String name) {
    return tradeRepository
        .findByName(name)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.noContent().build());
  }
}
