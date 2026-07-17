package com.example.privacypolicy.adapter.outbound.persistence;

import jakarta.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

/** JPA persistence model for a privacy policy. Lives in the adapter, never leaks to the domain. */
@Getter
@Entity
@Table(name = "privacy_policy")
@AllArgsConstructor
public class PrivacyPolicyJpaEntity {

  /**
   * @return primary key.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * @return version number.
   */
  @Column(nullable = false)
  private int version;

  /**
   * @return policy text body.
   */
  // the large text body, deliberately omitted from summary queries
  @Lob
  @Column(nullable = false)
  private String content;

  /**
   * @return creation timestamp.
   */
  @Column(name = "created_date", nullable = false)
  private Instant createdDate;

  /**
   * @return effective start date.
   */
  @Column(name = "effective_from", nullable = false)
  private LocalDate effectiveFrom;

  /**
   * @return effective end date, may be null.
   */
  @Column(name = "effective_to")
  private LocalDate effectiveTo;

  /** No-arg constructor required by JPA. */
  protected PrivacyPolicyJpaEntity() {}
}
