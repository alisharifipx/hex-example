package com.example.privacypolicy.adapter.inbound.http;

import com.example.privacypolicy.core.port.inbound.CreatePrivacyPolicyUseCase;
import com.example.privacypolicy.core.port.inbound.CreatePrivacyPolicyUseCase.CreateCommand;
import com.example.privacypolicy.core.port.inbound.GetPrivacyPolicyUseCase;
import com.example.privacypolicy.core.port.inbound.ListPrivacyPolicySummariesUseCase;
import com.example.privacypolicy.core.port.inbound.UpdatePrivacyPolicyUseCase;
import com.example.privacypolicy.core.port.inbound.UpdatePrivacyPolicyUseCase.UpdateCommand;
import com.example.privacypolicy.domain.model.PrivacyPolicy;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/** Inbound web adapter: exposes the privacy policy use cases over REST. */
@RestController
@RequestMapping("/api/privacy-policies")
@AllArgsConstructor
public class PrivacyPolicyController {

  private final GetPrivacyPolicyUseCase getUseCase;
  private final ListPrivacyPolicySummariesUseCase listUseCase;
  private final CreatePrivacyPolicyUseCase createUseCase;
  private final UpdatePrivacyPolicyUseCase updateUseCase;

  /** GET one full policy by id. */
  @GetMapping("/{id}")
  public PrivacyPolicyResponse getOne(@PathVariable Long id) {
    return PrivacyPolicyResponse.from(getUseCase.getById(id));
  }

  /** GET metadata-only summaries of all policies (no content bodies). */
  @GetMapping
  public List<PrivacyPolicySummaryResponse> listSummaries() {
    // map each domain summary to its web response
    return listUseCase.listAll().stream().map(PrivacyPolicySummaryResponse::from).toList();
  }

  /** POST a new policy. */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PrivacyPolicyResponse create(@RequestBody CreatePrivacyPolicyRequest request) {
    PrivacyPolicy created =
        createUseCase.create(
            new CreateCommand(request.content(), request.effectiveFrom(), request.effectiveTo()));
    return PrivacyPolicyResponse.from(created);
  }

  /** PUT an update to an existing policy. */
  @PutMapping("/{id}")
  public PrivacyPolicyResponse update(
      @PathVariable Long id, @RequestBody UpdatePrivacyPolicyRequest request) {
    PrivacyPolicy updated =
        updateUseCase.update(
            new UpdateCommand(
                id, request.content(), request.effectiveFrom(), request.effectiveTo()));
    return PrivacyPolicyResponse.from(updated);
  }
}
