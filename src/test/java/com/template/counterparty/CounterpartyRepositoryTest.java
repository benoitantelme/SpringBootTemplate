package com.template.counterparty;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.template.counterparty.db.CounterpartyRepository;
import com.template.counterparty.model.Counterparty;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest()
public class CounterpartyRepositoryTest {

  @Autowired CounterpartyRepository repo;

  @Test
  public void testRepo() {
    assertEquals(3, repo.findAll().size());

    List<Counterparty> result = repo.findByName("BNP");
    assertEquals(1, result.size());
    assertEquals("BNP", result.getFirst().getName());
  }
}
