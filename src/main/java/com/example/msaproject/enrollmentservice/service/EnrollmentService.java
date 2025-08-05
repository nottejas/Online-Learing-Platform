package com.example.msaproject.enrollmentservice.service;

import com.example.msaproject.enrollmentservice.dto.EnrollmentCreateRequest;
import com.example.msaproject.enrollmentservice.dto.EnrollmentResponse;
import com.example.msaproject.enrollmentservice.dto.ProgressUpdateRequest;
import com.example.msaproject.enrollmentservice.entity.Enrollment;
import com.example.msaproject.enrollmentservice.entity.EnrollmentStatus;
import com.example.msaproject.enrollmentservice.exception.EnrollmentAlreadyExistsException;
import com.example.msaproject.enrollmentservice.exception.EnrollmentNotFoundException;
import com.example.msaproject.enrollmentservice.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EnrollmentService {
    
    private final EnrollmentRepository enrollmentRepository;
    
    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }
    
    public EnrollmentResponse createEnrollment(EnrollmentCreateRequest request) {
        // Check if enrollment already exists
        if (enrollmentRepository.existsByStudentIdAndCourseId(request.getStudentId(), request.getCourseId())) {
            throw new EnrollmentAlreadyExistsException("Student is already enrolled in this course");
        }
        
        Enrollment enrollment = new Enrollment();
        enrollment.setStudentId(request.getStudentId());
        enrollment.setCourseId(request.getCourseId());
        enrollment.setCourseTitle(request.getCourseTitle());
        enrollment.setInstructorId(request.getInstructorId());
        enrollment.setInstructorName(request.getInstructorName());
        enrollment.setStudentName(request.getStudentName());
        enrollment.setStudentEmail(request.getStudentEmail());
        enrollment.setEnrollmentPrice(request.getEnrollmentPrice());
        enrollment.setTotalLectures(request.getTotalLectures() != null ? request.getTotalLectures() : 0);
        enrollment.setLastAccessedAt(LocalDateTime.now());
        
        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
        return convertToResponse(savedEnrollment);
    }
    
    @Transactional(readOnly = true)
    public EnrollmentResponse getEnrollmentById(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new EnrollmentNotFoundException("Enrollment not found with id: " + id));
        return convertToResponse(enrollment);
    }
    
    @Transactional(readOnly = true)
    public Page<EnrollmentResponse> getEnrollmentsByStudent(Long studentId, Pageable pageable) {
        Page<Enrollment> enrollments = enrollmentRepository.findByStudentId(studentId, pageable);
        return enrollments.map(this::convertToResponse);
    }
    
    @Transactional(readOnly = true)
    public Page<EnrollmentResponse> getEnrollmentsByCourse(Long courseId, Pageable pageable) {
        Page<Enrollment> enrollments = enrollmentRepository.findByCourseId(courseId, pageable);
        return enrollments.map(this::convertToResponse);
    }
    
    @Transactional(readOnly = true)
    public Page<EnrollmentResponse> getEnrollmentsByInstructor(Long instructorId, Pageable pageable) {
        Page<Enrollment> enrollments = enrollmentRepository.findByInstructorId(instructorId, pageable);
        return enrollments.map(this::convertToResponse);
    }
    
    @Transactional(readOnly = true)
    public EnrollmentResponse getEnrollmentByStudentAndCourse(Long studentId, Long courseId) {
        Enrollment enrollment = enrollmentRepository.findByStudentIdAndCourseId(studentId, courseId)
                .orElseThrow(() -> new EnrollmentNotFoundException("Enrollment not found for student " + studentId + " and course " + courseId));
        return convertToResponse(enrollment);
    }
    
    @Transactional(readOnly = true)
    public boolean isStudentEnrolled(Long studentId, Long courseId) {
        return enrollmentRepository.existsByStudentIdAndCourseId(studentId, courseId);
    }
    
    @Transactional(readOnly = true)
    public Page<EnrollmentResponse> getEnrollmentsByStatus(EnrollmentStatus status, Pageable pageable) {
        Page<Enrollment> enrollments = enrollmentRepository.findByStatus(status, pageable);
        return enrollments.map(this::convertToResponse);
    }
    
    @Transactional(readOnly = true)
    public Page<EnrollmentResponse> getCompletedEnrollments(Pageable pageable) {
        Page<Enrollment> enrollments = enrollmentRepository.findCompletedEnrollments(pageable);
        return enrollments.map(this::convertToResponse);
    }
    
    @Transactional(readOnly = true)
    public List<EnrollmentResponse> getCompletedEnrollmentsByStudent(Long studentId) {
        List<Enrollment> enrollments = enrollmentRepository.findCompletedEnrollmentsByStudent(studentId);
        return enrollments.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<EnrollmentResponse> getRecentlyAccessedByStudent(Long studentId) {
        LocalDateTime fromDate = LocalDateTime.now().minusDays(30); // Last 30 days
        List<Enrollment> enrollments = enrollmentRepository.findRecentlyAccessedByStudent(studentId, fromDate);
        return enrollments.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<EnrollmentResponse> getCertificatesByStudent(Long studentId) {
        List<Enrollment> enrollments = enrollmentRepository.findCertificatesByStudent(studentId);
        return enrollments.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    public EnrollmentResponse updateProgress(Long id, ProgressUpdateRequest request) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new EnrollmentNotFoundException("Enrollment not found with id: " + id));
        
        if (request.getProgressPercentage() != null) {
            enrollment.setProgressPercentage(request.getProgressPercentage());
            
            // Auto-complete if progress reaches 100%
            if (request.getProgressPercentage().compareTo(new BigDecimal("100")) >= 0) {
                enrollment.setStatus(EnrollmentStatus.COMPLETED);
                enrollment.setCompletionDate(LocalDateTime.now());
            }
        }
        
        if (request.getCompletedLectures() != null) {
            enrollment.setCompletedLectures(request.getCompletedLectures());
            
            // Calculate progress percentage if total lectures is available
            if (enrollment.getTotalLectures() != null && enrollment.getTotalLectures() > 0) {
                BigDecimal progress = new BigDecimal(request.getCompletedLectures())
                        .divide(new BigDecimal(enrollment.getTotalLectures()), 4, java.math.RoundingMode.HALF_UP)
                        .multiply(new BigDecimal("100"));
                enrollment.setProgressPercentage(progress);
                
                // Auto-complete if all lectures are completed
                if (request.getCompletedLectures().equals(enrollment.getTotalLectures())) {
                    enrollment.setStatus(EnrollmentStatus.COMPLETED);
                    enrollment.setCompletionDate(LocalDateTime.now());
                }
            }
        }
        
        enrollment.setLastAccessedAt(LocalDateTime.now());
        
        Enrollment updatedEnrollment = enrollmentRepository.save(enrollment);
        return convertToResponse(updatedEnrollment);
    }
    
    public EnrollmentResponse updateLastAccessed(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new EnrollmentNotFoundException("Enrollment not found with id: " + id));
        
        enrollment.setLastAccessedAt(LocalDateTime.now());
        
        Enrollment updatedEnrollment = enrollmentRepository.save(enrollment);
        return convertToResponse(updatedEnrollment);
    }
    
    public EnrollmentResponse markAsCompleted(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new EnrollmentNotFoundException("Enrollment not found with id: " + id));
        
        enrollment.setStatus(EnrollmentStatus.COMPLETED);
        enrollment.setProgressPercentage(new BigDecimal("100"));
        enrollment.setCompletionDate(LocalDateTime.now());
        enrollment.setLastAccessedAt(LocalDateTime.now());
        
        if (enrollment.getTotalLectures() != null) {
            enrollment.setCompletedLectures(enrollment.getTotalLectures());
        }
        
        Enrollment updatedEnrollment = enrollmentRepository.save(enrollment);
        return convertToResponse(updatedEnrollment);
    }
    
    public EnrollmentResponse suspendEnrollment(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new EnrollmentNotFoundException("Enrollment not found with id: " + id));
        
        enrollment.setStatus(EnrollmentStatus.SUSPENDED);
        
        Enrollment updatedEnrollment = enrollmentRepository.save(enrollment);
        return convertToResponse(updatedEnrollment);
    }
    
    public EnrollmentResponse activateEnrollment(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new EnrollmentNotFoundException("Enrollment not found with id: " + id));
        
        enrollment.setStatus(EnrollmentStatus.ACTIVE);
        
        Enrollment updatedEnrollment = enrollmentRepository.save(enrollment);
        return convertToResponse(updatedEnrollment);
    }
    
    public EnrollmentResponse cancelEnrollment(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new EnrollmentNotFoundException("Enrollment not found with id: " + id));
        
        enrollment.setStatus(EnrollmentStatus.CANCELLED);
        
        Enrollment updatedEnrollment = enrollmentRepository.save(enrollment);
        return convertToResponse(updatedEnrollment);
    }
    
    public EnrollmentResponse issueCertificate(Long id, String certificateUrl) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new EnrollmentNotFoundException("Enrollment not found with id: " + id));
        
        enrollment.setCertificateIssued(true);
        enrollment.setCertificateUrl(certificateUrl);
        
        // Ensure enrollment is marked as completed
        if (!enrollment.getStatus().equals(EnrollmentStatus.COMPLETED)) {
            enrollment.setStatus(EnrollmentStatus.COMPLETED);
            enrollment.setProgressPercentage(new BigDecimal("100"));
            enrollment.setCompletionDate(LocalDateTime.now());
        }
        
        Enrollment updatedEnrollment = enrollmentRepository.save(enrollment);
        return convertToResponse(updatedEnrollment);
    }
    
    public void deleteEnrollment(Long id) {
        if (!enrollmentRepository.existsById(id)) {
            throw new EnrollmentNotFoundException("Enrollment not found with id: " + id);
        }
        enrollmentRepository.deleteById(id);
    }
    
    @Transactional(readOnly = true)
    public long getEnrollmentCountByStudent(Long studentId) {
        return enrollmentRepository.countByStudentId(studentId);
    }
    
    @Transactional(readOnly = true)
    public long getEnrollmentCountByCourse(Long courseId) {
        return enrollmentRepository.countByCourseId(courseId);
    }
    
    @Transactional(readOnly = true)
    public long getEnrollmentCountByInstructor(Long instructorId) {
        return enrollmentRepository.countByInstructorId(instructorId);
    }
    
    @Transactional(readOnly = true)
    public BigDecimal getAverageProgressByCourse(Long courseId) {
        BigDecimal avgProgress = enrollmentRepository.getAverageProgressByCourse(courseId);
        return avgProgress != null ? avgProgress : BigDecimal.ZERO;
    }
    
    @Transactional(readOnly = true)
    public BigDecimal getTotalRevenueByInstructor(Long instructorId) {
        BigDecimal revenue = enrollmentRepository.getTotalRevenueByInstructor(instructorId);
        return revenue != null ? revenue : BigDecimal.ZERO;
    }
    
    @Transactional(readOnly = true)
    public BigDecimal getTotalRevenueByCourse(Long courseId) {
        BigDecimal revenue = enrollmentRepository.getTotalRevenueByCourse(courseId);
        return revenue != null ? revenue : BigDecimal.ZERO;
    }
    
    private EnrollmentResponse convertToResponse(Enrollment enrollment) {
        EnrollmentResponse response = new EnrollmentResponse();
        response.setId(enrollment.getId());
        response.setStudentId(enrollment.getStudentId());
        response.setCourseId(enrollment.getCourseId());
        response.setCourseTitle(enrollment.getCourseTitle());
        response.setInstructorId(enrollment.getInstructorId());
        response.setInstructorName(enrollment.getInstructorName());
        response.setStudentName(enrollment.getStudentName());
        response.setStudentEmail(enrollment.getStudentEmail());
        response.setEnrollmentPrice(enrollment.getEnrollmentPrice());
        response.setStatus(enrollment.getStatus());
        response.setProgressPercentage(enrollment.getProgressPercentage());
        response.setCompletedLectures(enrollment.getCompletedLectures());
        response.setTotalLectures(enrollment.getTotalLectures());
        response.setLastAccessedAt(enrollment.getLastAccessedAt());
        response.setCompletionDate(enrollment.getCompletionDate());
        response.setCertificateIssued(enrollment.getCertificateIssued());
        response.setCertificateUrl(enrollment.getCertificateUrl());
        response.setEnrolledAt(enrollment.getEnrolledAt());
        response.setUpdatedAt(enrollment.getUpdatedAt());
        return response;
    }
}
