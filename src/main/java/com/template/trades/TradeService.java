//package com.template.trades;
//
//import com.template.trades.model.Currency;
//import com.template.trades.model.Trade;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import javax.sql.DataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class TradeService {
//
//  @Autowired public DataSource dataSource;
//
//  public void createTrade(Trade trade) {
//    try (Connection conn = dataSource.getConnection()) {
//      PreparedStatement pstmt =
//          conn.prepareStatement(
//              "insert into trades(name,counterparty,amount,currency) values(?,?,?,?)");
//      pstmt.setString(1, trade.getName());
//      pstmt.setString(2, trade.getCounterparty());
//      pstmt.setDouble(3, trade.getAmount());
//      pstmt.setString(4, trade.getCurrency().toString());
//      pstmt.execute();
//    } catch (SQLException e) {
//      throw new RuntimeException(e);
//    }
//  }
//
//  public List<Trade> getAllTrades() {
//    List<Trade> trades = new ArrayList<>();
//
//    try (Connection conn = dataSource.getConnection()) {
//      PreparedStatement pstmt =
//          conn.prepareStatement("select name,counterparty,amount,currency from trades");
//      ResultSet rs = pstmt.executeQuery();
//      while (rs.next()) {
//        String name = rs.getString("name");
//        Trade trade = getTrade(name, rs);
//        trades.add(trade);
//      }
//    } catch (SQLException e) {
//      throw new RuntimeException(e);
//    }
//    return trades;
//  }
//
//  public Optional<Trade> getTrade(String name) {
//    Optional<Trade> trade = Optional.empty();
//
//    try (Connection conn = dataSource.getConnection()) {
//      PreparedStatement pstmt =
//          conn.prepareStatement("select counterparty,amount,currency from trades where name=?");
//      pstmt.setString(1, name);
//      ResultSet rs = pstmt.executeQuery();
//      while (rs.next()) {
//        trade = Optional.of(getTrade(name, rs));
//      }
//    } catch (SQLException e) {
//      throw new RuntimeException(e);
//    }
//    return trade;
//  }
//
//  private static Trade getTrade(String name, ResultSet rs) throws SQLException {
//    Trade trade;
//    String counterparty = rs.getString("counterparty");
//    double value = rs.getLong("amount");
//
//    Currency currency = null;
//    try {
//      currency = Currency.valueOf(rs.getString("currency"));
//    } catch (IllegalArgumentException e) {
//      throw new RuntimeException(e);
//    }
//
//    trade = new Trade(name, counterparty, value, currency);
//    return trade;
//  }
//}
