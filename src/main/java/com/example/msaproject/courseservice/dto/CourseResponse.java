package com.example.msaproject.courseservice.dto;

import com.example.msaproject.courseservice.entity.CourseCategory;
import com.example.msaproject.courseservice.entity.CourseLevel;
import com.example.msaproject.courseservice.entity.CourseStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CourseResponse {
    
    private Long id;
    private String title;
    private String description;
    private String shortDescription;
    private Long instructorId;
    private String instructorName;
    private CourseCategory category;
    private CourseLevel level;
    private BigDecimal price;
    private BigDecimal discountPrice;
    private String thumbnailUrl;
    private String previewVideoUrl;
    private Integer durationHours;
    private Integer durationMinutes;
    private Integer totalLectures;
    private Integer totalStudents;
    private BigDecimal averageRating;
    private Integer totalReviews;
    private List<String> tags;
    private List<String> requirements;
    private List<String> learningOutcomes;
    private CourseStatus status;
    private Boolean isPublished;
    private Boolean isFeatured;
    private LocalDateTime publishedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Constructors
    public CourseResponse() {}
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
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
    
    public Integer getTotalLectures() {
        return totalLectures;
    }
    
    public void setTotalLectures(Integer totalLectures) {
        this.totalLectures = totalLectures;
    }
    
    public Integer getTotalStudents() {
        return totalStudents;
    }
    
    public void setTotalStudents(Integer totalStudents) {
        this.totalStudents = totalStudents;
    }
    
    public BigDecimal getAverageRating() {
        return averageRating;
    }
    
    public void setAverageRating(BigDecimal averageRating) {
        this.averageRating = averageRating;
    }
    
    public Integer getTotalReviews() {
        return totalReviews;
    }
    
    public void setTotalReviews(Integer totalReviews) {
        this.totalReviews = totalReviews;
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
    
    public CourseStatus getStatus() {
        return status;
    }
    
    public void setStatus(CourseStatus status) {
        this.status = status;
    }
    
    public Boolean getIsPublished() {
        return isPublished;
    }
    
    public void setIsPublished(Boolean isPublished) {
        this.isPublished = isPublished;
    }
    
    public Boolean getIsFeatured() {
        return isFeatured;
    }
    
    public void setIsFeatured(Boolean isFeatured) {
        this.isFeatured = isFeatured;
    }
    
    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }
    
    public void setPublishedAt(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public String getFormattedDuration() {
        if (durationHours == null && durationMinutes == null) {
            return "Duration not specified";
        }
        
        StringBuilder duration = new StringBuilder();
        if (durationHours != null && durationHours > 0) {
            duration.append(durationHours).append("h ");
        }
        if (durationMinutes != null && durationMinutes > 0) {
            duration.append(durationMinutes).append("m");
        }
        
        return duration.toString().trim();
    }
    
    public BigDecimal getEffectivePrice() {
        return discountPrice != null ? discountPrice : price;
    }
}
