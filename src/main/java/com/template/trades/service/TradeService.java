package com.template.trades.service;

import com.template.trades.model.Currency;
import com.template.trades.model.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TradeService {

    @Autowired
    public DataSource dataSource;

    public void createTrade(Trade trade) {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(
                    "insert into trades(name,counterparty,amount,currency) values(?,?,?,?)"
            );
            pstmt.setString(1, trade.getName());
            pstmt.setString(2, trade.getCounterparty());
            pstmt.setDouble(3, trade.getAmount());
            pstmt.setString(4, trade.getCurrency().toString());
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Trade> getAllTrades() {
        List<Trade> trades = new ArrayList<>();

        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(
                    "select name,counterparty,amount,currency from trades"
            );
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String counterparty = rs.getString("counterparty");
                double value = rs.getLong("amount");

                Currency currency = null;
                try {
                    currency = Currency.valueOf(rs.getString("currency"));
                } catch (IllegalArgumentException e) {
                    throw new RuntimeException(e);
                }

                trades.add(new Trade(name, counterparty, value, currency));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return trades;
    }

}
