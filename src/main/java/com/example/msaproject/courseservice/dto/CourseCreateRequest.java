package com.example.msaproject.courseservice.dto;

import com.example.msaproject.courseservice.entity.CourseCategory;
import com.example.msaproject.courseservice.entity.CourseLevel;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

public class CourseCreateRequest {
    
    @NotBlank(message = "Course title is required")
    @Size(max = 255, message = "Course title must not exceed 255 characters")
    private String title;
    
    @NotBlank(message = "Course description is required")
    private String description;
    
    @Size(max = 500, message = "Short description must not exceed 500 characters")
    private String shortDescription;
    
    @NotNull(message = "Instructor ID is required")
    private Long instructorId;
    
    @NotBlank(message = "Instructor name is required")
    private String instructorName;
    
    @NotNull(message = "Course category is required")
    private CourseCategory category;
    
    private CourseLevel level = CourseLevel.BEGINNER;
    
    @NotNull(message = "Course price is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Price must be non-negative")
    private BigDecimal price;
    
    private BigDecimal discountPrice;
    private String thumbnailUrl;
    private String previewVideoUrl;
    private Integer durationHours;
    private Integer durationMinutes;
    private List<String> tags;
    private List<String> requirements;
    private List<String> learningOutcomes;
    
    // Constructors
    public CourseCreateRequest() {}
    
    public CourseCreateRequest(String title, String description, Long instructorId, String instructorName, 
                              CourseCategory category, BigDecimal price) {
        this.title = title;
        this.description = description;
        this.instructorId = instructorId;
        this.instructorName = instructorName;
        this.category = category;
        this.price = price;
    }
    
    // Getters and Setters
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getShortDescription() {
        return shortDescription;
    }
    
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
    
    public Long getInstructorId() {
        return instructorId;
    }
    
    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
    }
    
    public String getInstructorName() {
        return instructorName;
    }
    
    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }
    
    public CourseCategory getCategory() {
        return category;
    }
    
    public void setCategory(CourseCategory category) {
        this.category = category;
    }
    
    public CourseLevel getLevel() {
        return level;
    }
    
    public void setLevel(CourseLevel level) {
        this.level = level;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }
    
    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }
    
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
    
    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
    
    public String getPreviewVideoUrl() {
        return previewVideoUrl;
    }
    
    public void setPreviewVideoUrl(String previewVideoUrl) {
        this.previewVideoUrl = previewVideoUrl;
    }
    
    public Integer getDurationHours() {
        return durationHours;
    }
    
    public void setDurationHours(Integer durationHours) {
        this.durationHours = durationHours;
    }
    
    public Integer getDurationMinutes() {
        return durationMinutes;
    }
    
    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }
    
    public List<String> getTags() {
        return tags;
    }
    
    public void setTags(List<String> tags) {
        this.tags = tags;
    }
    
    public List<String> getRequirements() {
        return requirements;
    }
    
    public void setRequirements(List<String> requirements) {
        this.requirements = requirements;
    }
    
    public List<String> getLearningOutcomes() {
        return learningOutcomes;
    }
    
    public void setLearningOutcomes(List<String> learningOutcomes) {
        this.learningOutcomes = learningOutcomes;
    }
}
