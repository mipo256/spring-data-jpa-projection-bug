package org.example.repository;

import java.util.List;
import org.example.model.BankClient;
import org.example.model.ExactlySameBankClient;
import org.example.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, BankClient> {

  //language=sql
  @Query(value = """
      SELECT  
        DISTINCT ON (bk_client_id)
        bk_client_id, 
        bk_full_name,
        bk_external_id
      FROM orders
      WHERE bk_full_name LIKE '%' || :query || '%'
      """,
      nativeQuery = true)
  List<BankClient> findDistinctClients(String query);

  //language=sql
  @Query(value = """
      SELECT  
        DISTINCT ON (bk_client_id)
        bk_client_id AS clientId, 
        bk_full_name AS fullName,
        bk_external_id AS externalId
      FROM orders
      WHERE bk_full_name LIKE '%' || :query || '%'
      """,
      nativeQuery = true)
  List<ExactlySameBankClient> findDistinctClientsOnAnotherClient(String query);
}
