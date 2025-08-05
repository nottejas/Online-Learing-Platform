package com.example.msaproject.enrollmentservice.exception;

public class EnrollmentAlreadyExistsException extends RuntimeException {
    
    public EnrollmentAlreadyExistsException(String message) {
        super(message);
    }
    
    public EnrollmentAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
