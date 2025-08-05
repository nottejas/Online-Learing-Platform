package com.example.msaproject.enrollmentservice.controller;

import com.example.msaproject.enrollmentservice.dto.EnrollmentCreateRequest;
import com.example.msaproject.enrollmentservice.dto.EnrollmentResponse;
import com.example.msaproject.enrollmentservice.dto.ProgressUpdateRequest;
import com.example.msaproject.enrollmentservice.entity.EnrollmentStatus;
import com.example.msaproject.enrollmentservice.service.EnrollmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@CrossOrigin(origins = "*")
public class EnrollmentController {
    
    private final EnrollmentService enrollmentService;
    
    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }
    
    @PostMapping
    public ResponseEntity<EnrollmentResponse> createEnrollment(@Valid @RequestBody EnrollmentCreateRequest request) {
        EnrollmentResponse enrollment = enrollmentService.createEnrollment(request);
        return new ResponseEntity<>(enrollment, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentResponse> getEnrollmentById(@PathVariable Long id) {
        EnrollmentResponse enrollment = enrollmentService.getEnrollmentById(id);
        return ResponseEntity.ok(enrollment);
    }
    
    @GetMapping("/student/{studentId}")
    public ResponseEntity<Page<EnrollmentResponse>> getEnrollmentsByStudent(@PathVariable Long studentId, Pageable pageable) {
        Page<EnrollmentResponse> enrollments = enrollmentService.getEnrollmentsByStudent(studentId, pageable);
        return ResponseEntity.ok(enrollments);
    }
    
    @GetMapping("/course/{courseId}")
    public ResponseEntity<Page<EnrollmentResponse>> getEnrollmentsByCourse(@PathVariable Long courseId, Pageable pageable) {
        Page<EnrollmentResponse> enrollments = enrollmentService.getEnrollmentsByCourse(courseId, pageable);
        return ResponseEntity.ok(enrollments);
    }
    
    @GetMapping("/instructor/{instructorId}")
    public ResponseEntity<Page<EnrollmentResponse>> getEnrollmentsByInstructor(@PathVariable Long instructorId, Pageable pageable) {
        Page<EnrollmentResponse> enrollments = enrollmentService.getEnrollmentsByInstructor(instructorId, pageable);
        return ResponseEntity.ok(enrollments);
    }
    
    @GetMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<EnrollmentResponse> getEnrollmentByStudentAndCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        EnrollmentResponse enrollment = enrollmentService.getEnrollmentByStudentAndCourse(studentId, courseId);
        return ResponseEntity.ok(enrollment);
    }
    
    @GetMapping("/check/student/{studentId}/course/{courseId}")
    public ResponseEntity<Boolean> isStudentEnrolled(@PathVariable Long studentId, @PathVariable Long courseId) {
        boolean isEnrolled = enrollmentService.isStudentEnrolled(studentId, courseId);
        return ResponseEntity.ok(isEnrolled);
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<Page<EnrollmentResponse>> getEnrollmentsByStatus(@PathVariable EnrollmentStatus status, Pageable pageable) {
        Page<EnrollmentResponse> enrollments = enrollmentService.getEnrollmentsByStatus(status, pageable);
        return ResponseEntity.ok(enrollments);
    }
    
    @GetMapping("/completed")
    public ResponseEntity<Page<EnrollmentResponse>> getCompletedEnrollments(Pageable pageable) {
        Page<EnrollmentResponse> enrollments = enrollmentService.getCompletedEnrollments(pageable);
        return ResponseEntity.ok(enrollments);
    }
    
    @GetMapping("/student/{studentId}/completed")
    public ResponseEntity<List<EnrollmentResponse>> getCompletedEnrollmentsByStudent(@PathVariable Long studentId) {
        List<EnrollmentResponse> enrollments = enrollmentService.getCompletedEnrollmentsByStudent(studentId);
        return ResponseEntity.ok(enrollments);
    }
    
    @GetMapping("/student/{studentId}/recent")
    public ResponseEntity<List<EnrollmentResponse>> getRecentlyAccessedByStudent(@PathVariable Long studentId) {
        List<EnrollmentResponse> enrollments = enrollmentService.getRecentlyAccessedByStudent(studentId);
        return ResponseEntity.ok(enrollments);
    }
    
    @GetMapping("/certificates/student/{studentId}")
    public ResponseEntity<List<EnrollmentResponse>> getCertificatesByStudent(@PathVariable Long studentId) {
        List<EnrollmentResponse> enrollments = enrollmentService.getCertificatesByStudent(studentId);
        return ResponseEntity.ok(enrollments);
    }
    
    @PutMapping("/{id}/progress")
    public ResponseEntity<EnrollmentResponse> updateProgress(@PathVariable Long id, @Valid @RequestBody ProgressUpdateRequest request) {
        EnrollmentResponse enrollment = enrollmentService.updateProgress(id, request);
        return ResponseEntity.ok(enrollment);
    }
    
    @PutMapping("/{id}/access")
    public ResponseEntity<EnrollmentResponse> updateLastAccessed(@PathVariable Long id) {
        EnrollmentResponse enrollment = enrollmentService.updateLastAccessed(id);
        return ResponseEntity.ok(enrollment);
    }
    
    @PutMapping("/{id}/complete")
    public ResponseEntity<EnrollmentResponse> markAsCompleted(@PathVariable Long id) {
        EnrollmentResponse enrollment = enrollmentService.markAsCompleted(id);
        return ResponseEntity.ok(enrollment);
    }
    
    @PutMapping("/{id}/suspend")
    public ResponseEntity<EnrollmentResponse> suspendEnrollment(@PathVariable Long id) {
        EnrollmentResponse enrollment = enrollmentService.suspendEnrollment(id);
        return ResponseEntity.ok(enrollment);
    }
    
    @PutMapping("/{id}/activate")
    public ResponseEntity<EnrollmentResponse> activateEnrollment(@PathVariable Long id) {
        EnrollmentResponse enrollment = enrollmentService.activateEnrollment(id);
        return ResponseEntity.ok(enrollment);
    }
    
    @PutMapping("/{id}/cancel")
    public ResponseEntity<EnrollmentResponse> cancelEnrollment(@PathVariable Long id) {
        EnrollmentResponse enrollment = enrollmentService.cancelEnrollment(id);
        return ResponseEntity.ok(enrollment);
    }
    
    @PutMapping("/{id}/certificate")
    public ResponseEntity<EnrollmentResponse> issueCertificate(@PathVariable Long id, @RequestParam String certificateUrl) {
        EnrollmentResponse enrollment = enrollmentService.issueCertificate(id, certificateUrl);
        return ResponseEntity.ok(enrollment);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long id) {
        enrollmentService.deleteEnrollment(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/stats/count/student/{studentId}")
    public ResponseEntity<Long> getEnrollmentCountByStudent(@PathVariable Long studentId) {
        long count = enrollmentService.getEnrollmentCountByStudent(studentId);
        return ResponseEntity.ok(count);
    }
    
    @GetMapping("/stats/count/course/{courseId}")
    public ResponseEntity<Long> getEnrollmentCountByCourse(@PathVariable Long courseId) {
        long count = enrollmentService.getEnrollmentCountByCourse(courseId);
        return ResponseEntity.ok(count);
    }
    
    @GetMapping("/stats/count/instructor/{instructorId}")
    public ResponseEntity<Long> getEnrollmentCountByInstructor(@PathVariable Long instructorId) {
        long count = enrollmentService.getEnrollmentCountByInstructor(instructorId);
        return ResponseEntity.ok(count);
    }
    
    @GetMapping("/stats/progress/course/{courseId}")
    public ResponseEntity<BigDecimal> getAverageProgressByCourse(@PathVariable Long courseId) {
        BigDecimal avgProgress = enrollmentService.getAverageProgressByCourse(courseId);
        return ResponseEntity.ok(avgProgress);
    }
    
    @GetMapping("/stats/revenue/instructor/{instructorId}")
    public ResponseEntity<BigDecimal> getTotalRevenueByInstructor(@PathVariable Long instructorId) {
        BigDecimal revenue = enrollmentService.getTotalRevenueByInstructor(instructorId);
        return ResponseEntity.ok(revenue);
    }
    
    @GetMapping("/stats/revenue/course/{courseId}")
    public ResponseEntity<BigDecimal> getTotalRevenueByCourse(@PathVariable Long courseId) {
        BigDecimal revenue = enrollmentService.getTotalRevenueByCourse(courseId);
        return ResponseEntity.ok(revenue);
    }
}
