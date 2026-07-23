package com.example.privacypolicy.core.service;

import com.example.privacypolicy.core.domain.model.PrivacyPolicy;
import com.example.privacypolicy.core.port.inbound.GetPrivacyPolicyUseCase;
import com.example.privacypolicy.core.port.outbound.LoadPrivacyPolicyPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Use-case implementation: fetch a full policy by id. */
@Service
@Transactional(readOnly = true)
public class GetPrivacyPolicyService implements GetPrivacyPolicyUseCase {

  private final LoadPrivacyPolicyPort loadPort;

  /** Wire the outbound load port. */
  public GetPrivacyPolicyService(LoadPrivacyPolicyPort loadPort) {
    this.loadPort = loadPort;
  }

  /** Load by id or fail loudly when absent. */
  @Override
  public PrivacyPolicy getById(Long id) {
    // fail fast when the policy does not exist
    return loadPort
        .loadById(id)
        .orElseThrow(() -> new IllegalArgumentException("no policy with id " + id));
  }
}
