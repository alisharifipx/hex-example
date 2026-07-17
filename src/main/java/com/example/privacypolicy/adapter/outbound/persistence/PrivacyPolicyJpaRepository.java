package com.example.privacypolicy.adapter.outbound.persistence;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;

/** Spring Data repository declaring only the operations the ports actually need. */
public interface PrivacyPolicyJpaRepository extends Repository<PrivacyPolicyJpaEntity, Long> {

  /** Insert or update a policy row. */
  PrivacyPolicyJpaEntity save(PrivacyPolicyJpaEntity entity);

  /** Fetch a full policy by id. */
  Optional<PrivacyPolicyJpaEntity> findById(Long id);

  /** Return metadata-only projections, so the content LOB is never fetched. */
  List<PrivacyPolicySummaryProjection> findAllProjectedBy();
}
