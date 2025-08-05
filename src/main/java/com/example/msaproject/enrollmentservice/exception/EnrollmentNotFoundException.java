package com.example.msaproject.enrollmentservice.exception;

public class EnrollmentNotFoundException extends RuntimeException {
    
    public EnrollmentNotFoundException(String message) {
        super(message);
    }
    
    public EnrollmentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
