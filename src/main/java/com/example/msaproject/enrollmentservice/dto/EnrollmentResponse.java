package com.example.msaproject.enrollmentservice.dto;

import com.example.msaproject.enrollmentservice.entity.EnrollmentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class EnrollmentResponse {
    
    private Long id;
    private Long studentId;
    private Long courseId;
    private String courseTitle;
    private Long instructorId;
    private String instructorName;
    private String studentName;
    private String studentEmail;
    private BigDecimal enrollmentPrice;
    private EnrollmentStatus status;
    private BigDecimal progressPercentage;
    private Integer completedLectures;
    private Integer totalLectures;
    private LocalDateTime lastAccessedAt;
    private LocalDateTime completionDate;
    private Boolean certificateIssued;
    private String certificateUrl;
    private LocalDateTime enrolledAt;
    private LocalDateTime updatedAt;
    
    // Constructors
    public EnrollmentResponse() {}
    
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
    
    public String getCourseTitle() {
        return courseTitle;
    }
    
    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
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
    
    public String getStudentName() {
        return studentName;
    }
    
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    public String getStudentEmail() {
        return studentEmail;
    }
    
    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }
    
    public BigDecimal getEnrollmentPrice() {
        return enrollmentPrice;
    }
    
    public void setEnrollmentPrice(BigDecimal enrollmentPrice) {
        this.enrollmentPrice = enrollmentPrice;
    }
    
    public EnrollmentStatus getStatus() {
        return status;
    }
    
    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }
    
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
    
    public Integer getTotalLectures() {
        return totalLectures;
    }
    
    public void setTotalLectures(Integer totalLectures) {
        this.totalLectures = totalLectures;
    }
    
    public LocalDateTime getLastAccessedAt() {
        return lastAccessedAt;
    }
    
    public void setLastAccessedAt(LocalDateTime lastAccessedAt) {
        this.lastAccessedAt = lastAccessedAt;
    }
    
    public LocalDateTime getCompletionDate() {
        return completionDate;
    }
    
    public void setCompletionDate(LocalDateTime completionDate) {
        this.completionDate = completionDate;
    }
    
    public Boolean getCertificateIssued() {
        return certificateIssued;
    }
    
    public void setCertificateIssued(Boolean certificateIssued) {
        this.certificateIssued = certificateIssued;
    }
    
    public String getCertificateUrl() {
        return certificateUrl;
    }
    
    public void setCertificateUrl(String certificateUrl) {
        this.certificateUrl = certificateUrl;
    }
    
    public LocalDateTime getEnrolledAt() {
        return enrolledAt;
    }
    
    public void setEnrolledAt(LocalDateTime enrolledAt) {
        this.enrolledAt = enrolledAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public boolean isCompleted() {
        return progressPercentage != null && progressPercentage.compareTo(new BigDecimal("100")) >= 0;
    }
}
