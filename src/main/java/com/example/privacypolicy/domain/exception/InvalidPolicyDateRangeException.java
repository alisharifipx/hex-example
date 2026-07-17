package com.example.privacypolicy.domain.exception;

public class InvalidPolicyDateRangeException extends RuntimeException {
    public InvalidPolicyDateRangeException(String message) {
        super(message);
    }
}
