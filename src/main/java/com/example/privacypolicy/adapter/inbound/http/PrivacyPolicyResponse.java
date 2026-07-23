package com.example.privacypolicy.adapter.inbound.http;

import com.example.privacypolicy.core.domain.model.PrivacyPolicy;
import java.time.Instant;
import java.time.LocalDate;

/** Web response DTO for a full policy, includes the content body. */
public record PrivacyPolicyResponse(
    Long id,
    int version,
    String content,
    Instant createdDate,
    LocalDate effectiveFrom,
    LocalDate effectiveTo) {

  /** Build a response from a domain policy. */
  public static PrivacyPolicyResponse from(PrivacyPolicy p) {
    return new PrivacyPolicyResponse(
        p.id(), p.version(), p.content(), p.createdDate(), p.effectiveFrom(), p.effectiveTo());
  }
}
