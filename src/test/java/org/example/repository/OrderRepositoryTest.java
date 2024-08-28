package org.example.repository;

import java.util.List;
import org.example.model.BankClient;
import org.example.model.ExactlySameBankClient;
import org.example.model.Order;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@SpringBootTest
class OrderRepositoryTest extends AbstractIntegrationTest {

  @Autowired
  private OrderRepository sut;

  @Test
  @Sql(
      executionPhase = ExecutionPhase.BEFORE_TEST_METHOD,
      //language=sql
      statements = """
          CREATE TABLE IF NOT EXISTS orders(
            id BIGSERIAL PRIMARY KEY,
            number BIGINT,
            bk_client_id TEXT,
            bk_full_name TEXT,
            bk_external_id TEXT
          );
          """)
  @Sql(
      executionPhase = ExecutionPhase.AFTER_TEST_METHOD,
      //language=sql
      statements = "DROP TABLE IF EXISTS orders")
  void testBankClient() {
    sut.save(
        new Order()
            .setBankClient(
                new BankClient()
                    .setClientId("123")
                    .setFullName("anyName")
                    .setExternalId("321")
            )
            .setNumber(1)
    );
    List<BankClient> anyName = sut.findDistinctClients("anyName");
  }

  @Test
  @Sql(
      executionPhase = ExecutionPhase.BEFORE_TEST_METHOD,
      //language=sql
      statements = """
          CREATE TABLE IF NOT EXISTS orders(
            id BIGSERIAL PRIMARY KEY,
            number BIGINT,
            bk_client_id TEXT,
            bk_full_name TEXT,
            bk_external_id TEXT
          );
          """)
  @Sql(
      executionPhase = ExecutionPhase.AFTER_TEST_METHOD,
      //language=sql
      statements = "DROP TABLE IF EXISTS orders")
  void testExactlySameBankClient() {
    sut.save(
        new Order()
            .setBankClient(
                new BankClient()
                    .setClientId("123")
                    .setFullName("anyName")
                    .setExternalId("321")
            )
            .setNumber(1)
    );
    List<ExactlySameBankClient> anyName = sut.findDistinctClientsOnAnotherClient("anyName");
  }
}