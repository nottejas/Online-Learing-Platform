package com.example.msaproject.enrollmentservice.entity;

public enum EnrollmentStatus {
    ACTIVE("Active"),
    COMPLETED("Completed"),
    SUSPENDED("Suspended"),
    CANCELLED("Cancelled"),
    EXPIRED("Expired");
    
    private final String displayName;
    
    EnrollmentStatus(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    @Override
    public String toString() {
        return displayName;
    }
}
