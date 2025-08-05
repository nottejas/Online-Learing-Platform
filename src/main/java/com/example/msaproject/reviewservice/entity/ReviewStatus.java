package com.example.msaproject.reviewservice.entity;

public enum ReviewStatus {
    ACTIVE("Active"),
    PENDING("Pending"),
    APPROVED("Approved"),
    REJECTED("Rejected"),
    HIDDEN("Hidden"),
    FLAGGED("Flagged"),
    DELETED("Deleted");
    
    private final String displayName;
    
    ReviewStatus(String displayName) {
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
