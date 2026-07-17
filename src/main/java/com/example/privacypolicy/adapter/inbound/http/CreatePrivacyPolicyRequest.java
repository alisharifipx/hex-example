package com.example.privacypolicy.adapter.inbound.http;

import java.time.LocalDate;

/** Web request DTO for creating a policy. */
public record CreatePrivacyPolicyRequest(
    String content, LocalDate effectiveFrom, LocalDate effectiveTo) {}
