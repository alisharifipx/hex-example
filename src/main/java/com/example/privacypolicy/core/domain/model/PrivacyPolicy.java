package com.example.privacypolicy.core.domain.model;

import java.time.Instant;
import java.time.LocalDate;

/**
 * Core domain entity representing a versioned privacy policy. Pure Java, no framework deps.
 *
 * @param id
 * @param version
 * @param content
 * @param createdDate
 * @param effectiveFrom
 * @param effectiveTo
 */
public record PrivacyPolicy(
    Long id,
    int version,
    String content,
    Instant createdDate,
    LocalDate effectiveFrom,
    LocalDate effectiveTo) {

  /** Full constructor, used when rehydrating an existing policy from persistence. */
  public PrivacyPolicy {
    // guard: content must be present
    if (content == null || content.isBlank()) {
      throw new IllegalArgumentException("content required");
    }
    // guard: effective window must be valid when an end date exists
    if (effectiveTo != null && effectiveTo.isBefore(effectiveFrom)) {
      throw new IllegalArgumentException("effectiveTo before effectiveFrom");
    }
  }

  /** Factory for a brand-new policy not yet persisted (no id, version starts at 1). */
  public static PrivacyPolicy createNew(
      String content, LocalDate effectiveFrom, LocalDate effectiveTo) {
    return new PrivacyPolicy(null, 1, content, Instant.now(), effectiveFrom, effectiveTo);
  }

  /** Produce the next version of this policy with new content and effective window. */
  public PrivacyPolicy update(String newContent, LocalDate newFrom, LocalDate newTo) {
    return new PrivacyPolicy(
        this.id, this.version + 1, newContent, this.createdDate, newFrom, newTo);
  }
}
