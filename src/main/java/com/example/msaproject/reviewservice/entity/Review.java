package com.example.msaproject.reviewservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
public class Review {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Student ID is required")
    @Column(name = "student_id", nullable = false)
    private Long studentId;
    
    @NotNull(message = "Course ID is required")
    @Column(name = "course_id", nullable = false)
    private Long courseId;
    
    @NotNull(message = "Instructor ID is required")
    @Column(name = "instructor_id", nullable = false)
    private Long instructorId;
    
    @Column(name = "student_name", nullable = false)
    private String studentName;
    
    @Column(name = "course_title", nullable = false)
    private String courseTitle;
    
    @Column(name = "instructor_name", nullable = false)
    private String instructorName;
    
    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must not exceed 5")
    @Column(nullable = false)
    private Integer rating;
    
    @NotBlank(message = "Review title is required")
    @Size(max = 255, message = "Review title must not exceed 255 characters")
    @Column(name = "review_title", nullable = false)
    private String reviewTitle;
    
    @NotBlank(message = "Review content is required")
    @Size(max = 2000, message = "Review content must not exceed 2000 characters")
    @Column(name = "review_content", columnDefinition = "TEXT", nullable = false)
    private String reviewContent;
    
    @Column(name = "helpful_count")
    private Integer helpfulCount = 0;
    
    @Column(name = "unhelpful_count")
    private Integer unhelpfulCount = 0;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReviewStatus status = ReviewStatus.ACTIVE;
    
    @Column(name = "is_verified_purchase", nullable = false)
    private Boolean isVerifiedPurchase = false;
    
    @Column(name = "is_featured", nullable = false)
    private Boolean isFeatured = false;
    
    @Column(name = "instructor_reply", columnDefinition = "TEXT")
    private String instructorReply;
    
    @Column(name = "instructor_reply_at")
    private LocalDateTime instructorReplyAt;
    
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // Constructors
    public Review() {}
    
    public Review(Long studentId, Long courseId, Long instructorId, String studentName, 
                  String courseTitle, String instructorName, Integer rating, 
                  String reviewTitle, String reviewContent) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.instructorId = instructorId;
        this.studentName = studentName;
        this.courseTitle = courseTitle;
        this.instructorName = instructorName;
        this.rating = rating;
        this.reviewTitle = reviewTitle;
        this.reviewContent = reviewContent;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getStudentId() {
        return studentId;
    }
    
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
    
    public Long getCourseId() {
        return courseId;
    }
    
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
    
    public Long getInstructorId() {
        return instructorId;
    }
    
    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
    }
    
    public String getStudentName() {
        return studentName;
    }
    
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    public String getCourseTitle() {
        return courseTitle;
    }
    
    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }
    
    public String getInstructorName() {
        return instructorName;
    }
    
    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }
    
    public Integer getRating() {
        return rating;
    }
    
    public void setRating(Integer rating) {
        this.rating = rating;
    }
    
    public String getReviewTitle() {
        return reviewTitle;
    }
    
    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }
    
    public String getReviewContent() {
        return reviewContent;
    }
    
    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }
    
    public Integer getHelpfulCount() {
        return helpfulCount;
    }
    
    public void setHelpfulCount(Integer helpfulCount) {
        this.helpfulCount = helpfulCount;
    }
    
    public Integer getUnhelpfulCount() {
        return unhelpfulCount;
    }
    
    public void setUnhelpfulCount(Integer unhelpfulCount) {
        this.unhelpfulCount = unhelpfulCount;
    }
    
    public ReviewStatus getStatus() {
        return status;
    }
    
    public void setStatus(ReviewStatus status) {
        this.status = status;
    }
    
    public Boolean getIsVerifiedPurchase() {
        return isVerifiedPurchase;
    }
    
    public void setIsVerifiedPurchase(Boolean isVerifiedPurchase) {
        this.isVerifiedPurchase = isVerifiedPurchase;
    }
    
    public Boolean getIsFeatured() {
        return isFeatured;
    }
    
    public void setIsFeatured(Boolean isFeatured) {
        this.isFeatured = isFeatured;
    }
    
    public String getInstructorReply() {
        return instructorReply;
    }
    
    public void setInstructorReply(String instructorReply) {
        this.instructorReply = instructorReply;
    }
    
    public LocalDateTime getInstructorReplyAt() {
        return instructorReplyAt;
    }
    
    public void setInstructorReplyAt(LocalDateTime instructorReplyAt) {
        this.instructorReplyAt = instructorReplyAt;
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
    
    public Integer getTotalVotes() {
        return (helpfulCount != null ? helpfulCount : 0) + (unhelpfulCount != null ? unhelpfulCount : 0);
    }
    
    public Double getHelpfulnessRatio() {
        int total = getTotalVotes();
        if (total == 0) return 0.0;
        return (double) (helpfulCount != null ? helpfulCount : 0) / total;
    }
    
    public boolean hasInstructorReply() {
        return instructorReply != null && !instructorReply.trim().isEmpty();
    }
    
    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                ", rating=" + rating +
                ", reviewTitle='" + reviewTitle + '\'' +
                ", status=" + status +
                ", isVerifiedPurchase=" + isVerifiedPurchase +
                ", createdAt=" + createdAt +
                '}';
    }
}
