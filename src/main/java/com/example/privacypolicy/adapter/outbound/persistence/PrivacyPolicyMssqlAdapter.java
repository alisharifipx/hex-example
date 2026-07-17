package com.example.privacypolicy.adapter.outbound.persistence;

import com.example.privacypolicy.core.port.outbound.LoadPrivacyPolicyPort;
import com.example.privacypolicy.core.port.outbound.LoadPrivacyPolicySummariesPort;
import com.example.privacypolicy.core.port.outbound.SavePrivacyPolicyPort;
import com.example.privacypolicy.domain.model.PrivacyPolicy;
import com.example.privacypolicy.domain.model.PrivacyPolicySummary;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/** Outbound adapter: implements the persistence ports on top of Spring Data JPA. */
@Component
@AllArgsConstructor
public class PrivacyPolicyMssqlAdapter
    implements LoadPrivacyPolicyPort, SavePrivacyPolicyPort, LoadPrivacyPolicySummariesPort {

  private final PrivacyPolicyJpaRepository repository;
  private final PrivacyPolicyMapper mapper;

  /** Load a full policy by id and map it to the domain. */
  @Override
  public Optional<PrivacyPolicy> loadById(Long id) {
    return repository.findById(id).map(mapper::toDomain);
  }

  /** Persist a domain policy and return the saved state (with generated id). */
  @Override
  public PrivacyPolicy save(PrivacyPolicy policy) {
    PrivacyPolicyJpaEntity saved = repository.save(mapper.toEntity(policy));
    return mapper.toDomain(saved);
  }

  /** Load metadata-only projections and map them to domain summaries. */
  @Override
  public List<PrivacyPolicySummary> loadAllSummaries() {
    // stream projections through the mapper, content LOB never touched
    return repository.findAllProjectedBy().stream().map(mapper::toSummary).toList();
  }
}
