package com.example.privacypolicy.core.service;

import com.example.privacypolicy.core.domain.model.PrivacyPolicy;
import com.example.privacypolicy.core.port.inbound.UpdatePrivacyPolicyUseCase;
import com.example.privacypolicy.core.port.outbound.LoadPrivacyPolicyPort;
import com.example.privacypolicy.core.port.outbound.SavePrivacyPolicyPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Use-case implementation: update an existing policy, producing a new version. */
@Service
@Transactional
public class UpdatePrivacyPolicyService implements UpdatePrivacyPolicyUseCase {

  private final LoadPrivacyPolicyPort loadPort;
  private final SavePrivacyPolicyPort savePort;

  /** Wire the outbound load and save ports. */
  public UpdatePrivacyPolicyService(
      LoadPrivacyPolicyPort loadPort, SavePrivacyPolicyPort savePort) {
    this.loadPort = loadPort;
    this.savePort = savePort;
  }

  /** Load existing, apply the versioned update via the domain, persist. */
  @Override
  public PrivacyPolicy update(UpdateCommand command) {
    // fail fast when the target policy is missing
    PrivacyPolicy existing =
        loadPort
            .loadById(command.id())
            .orElseThrow(() -> new IllegalArgumentException("no policy with id " + command.id()));
    PrivacyPolicy updated =
        existing.update(command.content(), command.effectiveFrom(), command.effectiveTo());
    return savePort.save(updated);
  }
}
