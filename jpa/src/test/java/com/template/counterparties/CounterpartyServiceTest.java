package com.template.counterparties;

import static org.junit.jupiter.api.Assertions.*;

import com.template.counterparties.model.Counterparty;
import com.template.counterparties.service.CounterpartyService;
import java.util.Optional;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest()
@TestMethodOrder(MethodOrderer.MethodName.class)
public class CounterpartyServiceTest {

  @Autowired CounterpartyService service;

  private static final String BNP = "BNP";

  @Test
  public void testFind() {
    Optional<Counterparty> result = service.findCounterparty(BNP);
    assertTrue(result.isPresent());
    assertEquals(BNP, result.get().getName());
  }

  @Test
  public void testFindAll() {
    assertEquals(3, service.findCounterparties().size());
  }

  @Test
  public void testSave() {
    Counterparty cpty = new Counterparty("Test");
    Counterparty result = service.saveCounterparty(cpty);
    assertNotNull(result);
    assertEquals(cpty.getName(), result.getName());
  }
}
