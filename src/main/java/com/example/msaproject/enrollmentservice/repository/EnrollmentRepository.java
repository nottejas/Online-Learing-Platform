package com.example.msaproject.enrollmentservice.repository;

import com.example.msaproject.enrollmentservice.entity.Enrollment;
import com.example.msaproject.enrollmentservice.entity.EnrollmentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    
    List<Enrollment> findByStudentId(Long studentId);
    
    Page<Enrollment> findByStudentId(Long studentId, Pageable pageable);
    
    List<Enrollment> findByCourseId(Long courseId);
    
    Page<Enrollment> findByCourseId(Long courseId, Pageable pageable);
    
    List<Enrollment> findByInstructorId(Long instructorId);
    
    Page<Enrollment> findByInstructorId(Long instructorId, Pageable pageable);
    
    Optional<Enrollment> findByStudentIdAndCourseId(Long studentId, Long courseId);
    
    boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);
    
    List<Enrollment> findByStatus(EnrollmentStatus status);
    
    Page<Enrollment> findByStatus(EnrollmentStatus status, Pageable pageable);
    
    List<Enrollment> findByStudentIdAndStatus(Long studentId, EnrollmentStatus status);
    
    Page<Enrollment> findByStudentIdAndStatus(Long studentId, EnrollmentStatus status, Pageable pageable);
    
    List<Enrollment> findByCourseIdAndStatus(Long courseId, EnrollmentStatus status);
    
    Page<Enrollment> findByCourseIdAndStatus(Long courseId, EnrollmentStatus status, Pageable pageable);
    
    @Query("SELECT e FROM Enrollment e WHERE e.progressPercentage >= :minProgress")
    List<Enrollment> findByMinimumProgress(@Param("minProgress") BigDecimal minProgress);
    
    @Query("SELECT e FROM Enrollment e WHERE e.progressPercentage >= :minProgress")
    Page<Enrollment> findByMinimumProgress(@Param("minProgress") BigDecimal minProgress, Pageable pageable);
    
    @Query("SELECT e FROM Enrollment e WHERE e.progressPercentage = 100 AND e.status = 'COMPLETED'")
    List<Enrollment> findCompletedEnrollments();
    
    @Query("SELECT e FROM Enrollment e WHERE e.progressPercentage = 100 AND e.status = 'COMPLETED'")
    Page<Enrollment> findCompletedEnrollments(Pageable pageable);
    
    @Query("SELECT e FROM Enrollment e WHERE e.studentId = :studentId AND e.progressPercentage = 100 AND e.status = 'COMPLETED'")
    List<Enrollment> findCompletedEnrollmentsByStudent(@Param("studentId") Long studentId);
    
    @Query("SELECT e FROM Enrollment e WHERE e.courseId = :courseId AND e.progressPercentage = 100 AND e.status = 'COMPLETED'")
    List<Enrollment> findCompletedEnrollmentsByCourse(@Param("courseId") Long courseId);
    
    @Query("SELECT e FROM Enrollment e WHERE e.lastAccessedAt >= :fromDate")
    List<Enrollment> findRecentlyAccessed(@Param("fromDate") LocalDateTime fromDate);
    
    @Query("SELECT e FROM Enrollment e WHERE e.studentId = :studentId AND e.lastAccessedAt >= :fromDate")
    List<Enrollment> findRecentlyAccessedByStudent(@Param("studentId") Long studentId, @Param("fromDate") LocalDateTime fromDate);
    
    @Query("SELECT e FROM Enrollment e WHERE e.certificateIssued = true")
    List<Enrollment> findEnrollmentsWithCertificates();
    
    @Query("SELECT e FROM Enrollment e WHERE e.studentId = :studentId AND e.certificateIssued = true")
    List<Enrollment> findCertificatesByStudent(@Param("studentId") Long studentId);
    
    @Query("SELECT COUNT(e) FROM Enrollment e WHERE e.studentId = :studentId")
    long countByStudentId(@Param("studentId") Long studentId);
    
    @Query("SELECT COUNT(e) FROM Enrollment e WHERE e.courseId = :courseId")
    long countByCourseId(@Param("courseId") Long courseId);
    
    @Query("SELECT COUNT(e) FROM Enrollment e WHERE e.instructorId = :instructorId")
    long countByInstructorId(@Param("instructorId") Long instructorId);
    
    @Query("SELECT COUNT(e) FROM Enrollment e WHERE e.status = :status")
    long countByStatus(@Param("status") EnrollmentStatus status);
    
    @Query("SELECT COUNT(e) FROM Enrollment e WHERE e.progressPercentage = 100 AND e.status = 'COMPLETED'")
    long countCompletedEnrollments();
    
    @Query("SELECT COUNT(e) FROM Enrollment e WHERE e.studentId = :studentId AND e.progressPercentage = 100 AND e.status = 'COMPLETED'")
    long countCompletedEnrollmentsByStudent(@Param("studentId") Long studentId);
    
    @Query("SELECT COUNT(e) FROM Enrollment e WHERE e.courseId = :courseId AND e.progressPercentage = 100 AND e.status = 'COMPLETED'")
    long countCompletedEnrollmentsByCourse(@Param("courseId") Long courseId);
    
    @Query("SELECT AVG(e.progressPercentage) FROM Enrollment e WHERE e.courseId = :courseId")
    BigDecimal getAverageProgressByCourse(@Param("courseId") Long courseId);
    
    @Query("SELECT AVG(e.progressPercentage) FROM Enrollment e WHERE e.studentId = :studentId")
    BigDecimal getAverageProgressByStudent(@Param("studentId") Long studentId);
    
    @Query("SELECT SUM(e.enrollmentPrice) FROM Enrollment e WHERE e.instructorId = :instructorId")
    BigDecimal getTotalRevenueByInstructor(@Param("instructorId") Long instructorId);
    
    @Query("SELECT SUM(e.enrollmentPrice) FROM Enrollment e WHERE e.courseId = :courseId")
    BigDecimal getTotalRevenueByCourse(@Param("courseId") Long courseId);
}
