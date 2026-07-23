package com.example.privacypolicy.core.service;

import com.example.privacypolicy.core.domain.model.PrivacyPolicySummary;
import com.example.privacypolicy.core.port.inbound.ListPrivacyPolicySummariesUseCase;
import com.example.privacypolicy.core.port.outbound.LoadPrivacyPolicySummariesPort;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Use-case implementation: list policy metadata without content bodies. */
@Service
@Transactional(readOnly = true)
public class ListPrivacyPolicySummariesService implements ListPrivacyPolicySummariesUseCase {

  private final LoadPrivacyPolicySummariesPort loadSummariesPort;

  /** Wire the outbound summaries port. */
  public ListPrivacyPolicySummariesService(LoadPrivacyPolicySummariesPort loadSummariesPort) {
    this.loadSummariesPort = loadSummariesPort;
  }

  /** Delegate straight to the port, no extra domain logic needed. */
  @Override
  public List<PrivacyPolicySummary> listAll() {
    return loadSummariesPort.loadAllSummaries();
  }
}
