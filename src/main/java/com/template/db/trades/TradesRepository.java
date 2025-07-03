package com.template.db.trades;

import com.template.db.trades.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradesRepository extends JpaRepository<Trade, Long> {
}
