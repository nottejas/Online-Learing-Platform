package com.example.msaproject.userservice.entity;

public enum UserRole {
    STUDENT("Student"),
    INSTRUCTOR("Instructor"),
    ADMIN("Admin");
    
    private final String displayName;
    
    UserRole(String displayName) {
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
