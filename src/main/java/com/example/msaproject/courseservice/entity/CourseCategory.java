package com.example.msaproject.courseservice.entity;

public enum CourseCategory {
    PROGRAMMING("Programming"),
    WEB_DEVELOPMENT("Web Development"),
    MOBILE_DEVELOPMENT("Mobile Development"),
    DATA_SCIENCE("Data Science"),
    MACHINE_LEARNING("Machine Learning"),
    ARTIFICIAL_INTELLIGENCE("Artificial Intelligence"),
    CYBERSECURITY("Cybersecurity"),
    CLOUD_COMPUTING("Cloud Computing"),
    DEVOPS("DevOps"),
    DATABASE("Database"),
    UI_UX_DESIGN("UI/UX Design"),
    GRAPHIC_DESIGN("Graphic Design"),
    DIGITAL_MARKETING("Digital Marketing"),
    BUSINESS("Business"),
    PROJECT_MANAGEMENT("Project Management"),
    PHOTOGRAPHY("Photography"),
    VIDEO_EDITING("Video Editing"),
    MUSIC("Music"),
    LANGUAGE("Language"),
    PERSONAL_DEVELOPMENT("Personal Development"),
    HEALTH_FITNESS("Health & Fitness"),
    COOKING("Cooking"),
    OTHER("Other");
    
    private final String displayName;
    
    CourseCategory(String displayName) {
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
