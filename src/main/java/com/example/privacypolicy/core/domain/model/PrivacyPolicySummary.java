package com.example.privacypolicy.core.domain.model;

import java.time.Instant;
import java.time.LocalDate;

/** Lightweight metadata view of a privacy policy, deliberately excludes the content body. */
public record PrivacyPolicySummary(
    Long id, int version, Instant createdDate, LocalDate effectiveFrom, LocalDate effectiveTo) {}
