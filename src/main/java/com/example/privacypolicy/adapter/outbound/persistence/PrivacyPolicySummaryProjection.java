package com.example.privacypolicy.adapter.outbound.persistence;

import java.time.Instant;
import java.time.LocalDate;

/** Spring Data closed projection: selects metadata columns only, never loads the content LOB. */
public interface PrivacyPolicySummaryProjection {

  /**
   * @return primary key.
   */
  Long getId();

  /**
   * @return version number.
   */
  int getVersion();

  /**
   * @return creation timestamp.
   */
  Instant getCreatedDate();

  /**
   * @return effective start date.
   */
  LocalDate getEffectiveFrom();

  /**
   * @return effective end date, may be null.
   */
  LocalDate getEffectiveTo();
}
