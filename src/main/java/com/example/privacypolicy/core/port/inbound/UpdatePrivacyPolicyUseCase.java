package com.example.privacypolicy.core.port.inbound;

import com.example.privacypolicy.domain.model.PrivacyPolicy;
import java.time.LocalDate;

/** Inbound (driving) port: update an existing policy, bumping its version. */
public interface UpdatePrivacyPolicyUseCase {

  /**
   * @return the updated, persisted policy.
   */
  PrivacyPolicy update(UpdateCommand command);

  /** Input data for update, targets an existing policy by id. */
  record UpdateCommand(Long id, String content, LocalDate effectiveFrom, LocalDate effectiveTo) {}
}
