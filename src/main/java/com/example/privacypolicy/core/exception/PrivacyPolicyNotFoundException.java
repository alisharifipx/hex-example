package com.example.privacypolicy.core.exception;

public class PrivacyPolicyNotFoundException extends RuntimeException {
  public PrivacyPolicyNotFoundException(String id) {
    super("Privacy policy not found for id: " + id);
  }
}
