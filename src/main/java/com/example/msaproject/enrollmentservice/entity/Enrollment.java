package com.example.msaproject.enrollmentservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "enrollments")
public class Enrollment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Student ID is required")
    @Column(name = "student_id", nullable = false)
    private Long studentId;
    
    @NotNull(message = "Course ID is required")
    @Column(name = "course_id", nullable = false)
    private Long courseId;
    
    @Column(name = "course_title", nullable = false)
    private String courseTitle;
    
    @Column(name = "instructor_id", nullable = false)
    private Long instructorId;
    
    @Column(name = "instructor_name", nullable = false)
    private String instructorName;
    
    @Column(name = "student_name", nullable = false)
    private String studentName;
    
    @Column(name = "student_email", nullable = false)
    private String studentEmail;
    
    @Column(name = "enrollment_price", precision = 10, scale = 2, nullable = false)
    private BigDecimal enrollmentPrice;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnrollmentStatus status = EnrollmentStatus.ACTIVE;
    
    @Column(name = "progress_percentage", precision = 5, scale = 2)
    private BigDecimal progressPercentage = BigDecimal.ZERO;
    
    @Column(name = "completed_lectures")
    private Integer completedLectures = 0;
    
    @Column(name = "total_lectures")
    private Integer totalLectures = 0;
    
    @Column(name = "last_accessed_at")
    private LocalDateTime lastAccessedAt;
    
    @Column(name = "completion_date")
    private LocalDateTime completionDate;
    
    @Column(name = "certificate_issued", nullable = false)
    private Boolean certificateIssued = false;
    
    @Column(name = "certificate_url")
    private String certificateUrl;
    
    @CreationTimestamp
    @Column(name = "enrolled_at", nullable = false, updatable = false)
    private LocalDateTime enrolledAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // Constructors
    public Enrollment() {}
    
    public Enrollment(Long studentId, Long courseId, String courseTitle, Long instructorId, 
                     String instructorName, String studentName, String studentEmail, BigDecimal enrollmentPrice) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.instructorId = instructorId;
        this.instructorName = instructorName;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.enrollmentPrice = enrollmentPrice;
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
    
    @Override
    public String toString() {
        return "Enrollment{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                ", courseTitle='" + courseTitle + '\'' +
                ", status=" + status +
                ", progressPercentage=" + progressPercentage +
                ", enrolledAt=" + enrolledAt +
                '}';
    }
}
