package com.example.privacypolicy.core.port.outbound;

import com.example.privacypolicy.domain.model.PrivacyPolicy;

/** Outbound (driven) port: persist a policy (insert or update). */
public interface SavePrivacyPolicyPort {

  /**
   * @return the saved policy including any store-assigned id.
   */
  PrivacyPolicy save(PrivacyPolicy policy);
}
