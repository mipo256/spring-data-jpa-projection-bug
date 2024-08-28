package org.example.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ExactlySameBankClient {

  private String clientId;

  private String fullName;

  private String externalId;

  public ExactlySameBankClient(String clientId, String fullName, String externalId) {
    this.clientId = clientId;
    this.fullName = fullName;
    this.externalId = externalId;
  }
}
