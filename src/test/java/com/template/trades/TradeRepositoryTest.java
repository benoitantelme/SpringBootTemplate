// package com.template.trades;
//
// import com.template.trades.db.TradeRepository;
// import com.template.trades.model.Trade;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
//
// import java.util.Optional;
//
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertTrue;
//
// @SpringBootTest()
// public class TradeRepositoryTest {
//
//  @Autowired
//  TradeRepository tradeRepository;
//
//  @Test
//  public void testRepo() {
//    assertEquals(2, tradeRepository.findAll().size());
//
//    Optional<Trade> result = tradeRepository.findByName("BNP");
//    assertTrue(result.isPresent());
//    Trade t = result.get();
//    assertEquals("BNP", t.getName());
//  }
// }
