package com.example.privacypolicy.adapter.inbound.http;

import com.example.privacypolicy.core.domain.model.PrivacyPolicySummary;
import java.time.Instant;
import java.time.LocalDate;

/** Web response DTO for policy metadata, deliberately excludes content. */
public record PrivacyPolicySummaryResponse(
    Long id, int version, Instant createdDate, LocalDate effectiveFrom, LocalDate effectiveTo) {

  /** Build a summary response from a domain summary. */
  public static PrivacyPolicySummaryResponse from(PrivacyPolicySummary s) {
    return new PrivacyPolicySummaryResponse(
        s.id(), s.version(), s.createdDate(), s.effectiveFrom(), s.effectiveTo());
  }
}
