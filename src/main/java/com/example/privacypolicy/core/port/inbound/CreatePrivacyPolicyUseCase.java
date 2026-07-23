package com.example.privacypolicy.core.port.inbound;

import com.example.privacypolicy.core.domain.model.PrivacyPolicy;
import java.time.LocalDate;

/** Inbound (driving) port: create a new privacy policy. */
public interface CreatePrivacyPolicyUseCase {

  /**
   * @return the persisted policy (with assigned id).
   */
  PrivacyPolicy create(CreateCommand command);

  /** Input data for creation, keeps the port decoupled from web/persistence models. */
  record CreateCommand(String content, LocalDate effectiveFrom, LocalDate effectiveTo) {}
}
