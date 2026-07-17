package com.example.privacypolicy.core.port.inbound;

import com.example.privacypolicy.domain.model.PrivacyPolicy;

/** Inbound (driving) port: retrieve a single full privacy policy by id. */
public interface GetPrivacyPolicyUseCase {

  /**
   * @return the policy for the given id, or throws if none exists.
   */
  PrivacyPolicy getById(Long id);
}
