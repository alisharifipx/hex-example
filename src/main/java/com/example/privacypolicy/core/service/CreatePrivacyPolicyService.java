package com.example.privacypolicy.core.service;

import com.example.privacypolicy.core.domain.model.PrivacyPolicy;
import com.example.privacypolicy.core.port.inbound.CreatePrivacyPolicyUseCase;
import com.example.privacypolicy.core.port.outbound.SavePrivacyPolicyPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Use-case implementation: create a brand-new policy. */
@Service
@Transactional
@AllArgsConstructor
public class CreatePrivacyPolicyService implements CreatePrivacyPolicyUseCase {

  private final SavePrivacyPolicyPort savePort;

  /** Build a fresh domain policy (validation lives in the entity) and persist it. */
  @Override
  public PrivacyPolicy create(CreateCommand command) {
    PrivacyPolicy policy =
        PrivacyPolicy.createNew(command.content(), command.effectiveFrom(), command.effectiveTo());

    return savePort.save(policy);
  }
}
