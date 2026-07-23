package com.example.privacypolicy.core.port.inbound;

import com.example.privacypolicy.core.domain.model.PrivacyPolicySummary;
import java.util.List;

/** Inbound (driving) port: list policy metadata without loading content bodies. */
public interface ListPrivacyPolicySummariesUseCase {

  /**
   * @return all policy summaries (id, version, dates), never the content.
   */
  List<PrivacyPolicySummary> listAll();
}
