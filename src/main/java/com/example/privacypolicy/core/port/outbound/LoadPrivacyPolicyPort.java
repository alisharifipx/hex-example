package com.example.privacypolicy.core.port.outbound;

import com.example.privacypolicy.core.domain.model.PrivacyPolicy;
import java.util.Optional;

/** Outbound (driven) port: load a full privacy policy from a data store. */
public interface LoadPrivacyPolicyPort {

  /**
   * @return the policy for the given id, empty if not found.
   */
  Optional<PrivacyPolicy> loadById(Long id);
}
