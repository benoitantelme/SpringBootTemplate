package com.template.trades.service;

import com.template.books.model.Counterparty;
import com.template.books.repository.CounterpartyRepository;
import com.template.trades.model.Trade;
import com.template.trades.repository.TradeRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeService {

  @Autowired private TradeRepository tradeRepository;
  @Autowired private CounterpartyRepository counterpartyRepository;

  public Trade saveTrade(Trade trade) {
    String counterpartyName = trade.getCounterparty().getName();
    Counterparty counterparty =
        counterpartyRepository
            .findByName(counterpartyName)
            .orElseGet(() -> counterpartyRepository.save(trade.getCounterparty()));
    trade.setCounterparty(counterparty);
    return tradeRepository.save(trade);
  }

  public Optional<Trade> findTrade(String trade) {
    return tradeRepository.findByName(trade);
  }

  public List<Trade> findTrades() {
    return tradeRepository.findAll();
  }
}
