package com.example.msaproject.courseservice.entity;

public enum CourseStatus {
    DRAFT("Draft"),
    UNDER_REVIEW("Under Review"),
    APPROVED("Approved"),
    PUBLISHED("Published"),
    SUSPENDED("Suspended"),
    ARCHIVED("Archived");
    
    private final String displayName;
    
    CourseStatus(String displayName) {
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
