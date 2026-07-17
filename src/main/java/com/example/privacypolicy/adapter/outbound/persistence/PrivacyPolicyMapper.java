package com.example.privacypolicy.adapter.outbound.persistence;

import com.example.privacypolicy.domain.model.PrivacyPolicy;
import com.example.privacypolicy.domain.model.PrivacyPolicySummary;
import org.springframework.stereotype.Component;

/** Translates between JPA persistence models and pure domain objects. */
@Component
public class PrivacyPolicyMapper {

  /** Map a JPA entity to a domain policy. */
  PrivacyPolicy toDomain(PrivacyPolicyJpaEntity entity) {
    return new PrivacyPolicy(
        entity.getId(),
        entity.getVersion(),
        entity.getContent(),
        entity.getCreatedDate(),
        entity.getEffectiveFrom(),
        entity.getEffectiveTo());
  }

  /** Map a domain policy to a JPA entity for persistence. */
  PrivacyPolicyJpaEntity toEntity(PrivacyPolicy policy) {
    return new PrivacyPolicyJpaEntity(
        policy.id(),
        policy.version(),
        policy.content(),
        policy.createdDate(),
        policy.effectiveFrom(),
        policy.effectiveTo());
  }

  /** Map a metadata projection to a domain summary. */
  PrivacyPolicySummary toSummary(PrivacyPolicySummaryProjection p) {
    return new PrivacyPolicySummary(
        p.getId(), p.getVersion(), p.getCreatedDate(), p.getEffectiveFrom(), p.getEffectiveTo());
  }
}
