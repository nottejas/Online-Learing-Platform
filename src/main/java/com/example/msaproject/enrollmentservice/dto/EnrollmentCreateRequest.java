package com.example.msaproject.enrollmentservice.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class EnrollmentCreateRequest {
    
    @NotNull(message = "Student ID is required")
    private Long studentId;
    
    @NotNull(message = "Course ID is required")
    private Long courseId;
    
    @NotBlank(message = "Course title is required")
    private String courseTitle;
    
    @NotNull(message = "Instructor ID is required")
    private Long instructorId;
    
    @NotBlank(message = "Instructor name is required")
    private String instructorName;
    
    @NotBlank(message = "Student name is required")
    private String studentName;
    
    @NotBlank(message = "Student email is required")
    @Email(message = "Student email should be valid")
    private String studentEmail;
    
    @NotNull(message = "Enrollment price is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Enrollment price must be non-negative")
    private BigDecimal enrollmentPrice;
    
    private Integer totalLectures;
    
    // Constructors
    public EnrollmentCreateRequest() {}
    
    public EnrollmentCreateRequest(Long studentId, Long courseId, String courseTitle, Long instructorId, 
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
    
    public Integer getTotalLectures() {
        return totalLectures;
    }
    
    public void setTotalLectures(Integer totalLectures) {
        this.totalLectures = totalLectures;
    }
}
