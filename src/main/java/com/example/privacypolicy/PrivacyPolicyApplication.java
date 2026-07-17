package com.example.privacypolicy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** Spring Boot entry point. Component scan starts from this package root. */
@SpringBootApplication
public class PrivacyPolicyApplication {

  /** Boot the application. */
  public static void main(String[] args) {
    SpringApplication.run(PrivacyPolicyApplication.class, args);
  }
}
