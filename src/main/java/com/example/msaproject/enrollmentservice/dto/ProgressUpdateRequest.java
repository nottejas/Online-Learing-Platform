package com.example.msaproject.enrollmentservice.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;

import java.math.BigDecimal;

public class ProgressUpdateRequest {
    
    @DecimalMin(value = "0.0", inclusive = true, message = "Progress percentage must be non-negative")
    @DecimalMax(value = "100.0", inclusive = true, message = "Progress percentage cannot exceed 100")
    private BigDecimal progressPercentage;
    
    @Min(value = 0, message = "Completed lectures must be non-negative")
    private Integer completedLectures;
    
    // Constructors
    public ProgressUpdateRequest() {}
    
    public ProgressUpdateRequest(BigDecimal progressPercentage, Integer completedLectures) {
        this.progressPercentage = progressPercentage;
        this.completedLectures = completedLectures;
    }
    
    // Getters and Setters
    public BigDecimal getProgressPercentage() {
        return progressPercentage;
    }
    
    public void setProgressPercentage(BigDecimal progressPercentage) {
        this.progressPercentage = progressPercentage;
    }
    
    public Integer getCompletedLectures() {
        return completedLectures;
    }
    
    public void setCompletedLectures(Integer completedLectures) {
        this.completedLectures = completedLectures;
    }
}
