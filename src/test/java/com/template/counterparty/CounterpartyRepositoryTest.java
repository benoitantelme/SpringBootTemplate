package com.template.counterparty;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.template.counterparty.db.CounterpartyRepository;
import com.template.counterparty.model.Counterparty;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest()
public class CounterpartyRepositoryTest {

  @Autowired CounterpartyRepository repo;

  @Test
  public void testRepo() {
    assertEquals(3, repo.findAll().size());

    String BNP = "BNP";
    Optional<Counterparty> result = repo.findByName(BNP);
    assertTrue(result.isPresent());
    assertEquals(BNP, result.get().getName());
  }
}
