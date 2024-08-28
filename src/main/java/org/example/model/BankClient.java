package org.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Embeddable
public class BankClient {

  @Column(name = "bk_client_id")
  private String clientId;

  @Column(name = "bk_full_name")
  private String fullName;

  @Column(name = "bk_external_id")
  private String externalId;
}
