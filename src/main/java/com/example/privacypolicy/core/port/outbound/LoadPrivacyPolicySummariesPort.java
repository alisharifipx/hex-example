package com.example.privacypolicy.core.port.outbound;

import com.example.privacypolicy.core.domain.model.PrivacyPolicySummary;
import java.util.List;

/** Outbound (driven) port: load policy metadata only, no content column. */
public interface LoadPrivacyPolicySummariesPort {

  /**
   * @return summaries for every policy.
   */
  List<PrivacyPolicySummary> loadAllSummaries();
}
