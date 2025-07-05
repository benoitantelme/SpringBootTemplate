package com.template.trades;

import com.template.trades.model.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class TradeController {

    @Autowired
    TradeService tradeService;

    @GetMapping("/trades")
    public List<Trade> getTrades() {
        tradeService.getAllTrades();

        return tradeService.getAllTrades();
    }

    @GetMapping("/trade/{name}")
    public ResponseEntity<Trade> getTrade(@PathVariable String name) {
        tradeService.getAllTrades();
        Optional<Trade> opTrade = tradeService.getTrade(name);

        return tradeService.getTrade(name).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

}
