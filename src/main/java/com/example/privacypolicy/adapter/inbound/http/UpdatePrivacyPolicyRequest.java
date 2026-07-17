package com.example.privacypolicy.adapter.inbound.http;

import java.time.LocalDate;

/** Web request DTO for updating a policy (id comes from the URL path). */
public record UpdatePrivacyPolicyRequest(
    String content, LocalDate effectiveFrom, LocalDate effectiveTo) {}
