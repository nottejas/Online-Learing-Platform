package com.example.msaproject.paymentservice.repository;

import com.example.msaproject.paymentservice.entity.Payment;
import com.example.msaproject.paymentservice.entity.PaymentMethod;
import com.example.msaproject.paymentservice.entity.PaymentStatus;
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
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    
    List<Payment> findByStudentId(Long studentId);
    
    Page<Payment> findByStudentId(Long studentId, Pageable pageable);
    
    List<Payment> findByCourseId(Long courseId);
    
    Page<Payment> findByCourseId(Long courseId, Pageable pageable);
    
    List<Payment> findByInstructorId(Long instructorId);
    
    Page<Payment> findByInstructorId(Long instructorId, Pageable pageable);
    
    Optional<Payment> findByTransactionId(String transactionId);
    
    Optional<Payment> findByPaymentGatewayId(String paymentGatewayId);
    
    List<Payment> findByStatus(PaymentStatus status);
    
    Page<Payment> findByStatus(PaymentStatus status, Pageable pageable);
    
    List<Payment> findByPaymentMethod(PaymentMethod paymentMethod);
    
    Page<Payment> findByPaymentMethod(PaymentMethod paymentMethod, Pageable pageable);
    
    List<Payment> findByStudentIdAndStatus(Long studentId, PaymentStatus status);
    
    Page<Payment> findByStudentIdAndStatus(Long studentId, PaymentStatus status, Pageable pageable);
    
    List<Payment> findByCourseIdAndStatus(Long courseId, PaymentStatus status);
    
    Page<Payment> findByCourseIdAndStatus(Long courseId, PaymentStatus status, Pageable pageable);
    
    List<Payment> findByInstructorIdAndStatus(Long instructorId, PaymentStatus status);
    
    Page<Payment> findByInstructorIdAndStatus(Long instructorId, PaymentStatus status, Pageable pageable);
    
    @Query("SELECT p FROM Payment p WHERE p.amount BETWEEN :minAmount AND :maxAmount")
    List<Payment> findByAmountRange(@Param("minAmount") BigDecimal minAmount, @Param("maxAmount") BigDecimal maxAmount);
    
    @Query("SELECT p FROM Payment p WHERE p.amount BETWEEN :minAmount AND :maxAmount")
    Page<Payment> findByAmountRange(@Param("minAmount") BigDecimal minAmount, @Param("maxAmount") BigDecimal maxAmount, Pageable pageable);
    
    @Query("SELECT p FROM Payment p WHERE p.createdAt BETWEEN :startDate AND :endDate")
    List<Payment> findByDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT p FROM Payment p WHERE p.createdAt BETWEEN :startDate AND :endDate")
    Page<Payment> findByDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, Pageable pageable);
    
    @Query("SELECT p FROM Payment p WHERE p.status = 'COMPLETED' AND p.processedAt BETWEEN :startDate AND :endDate")
    List<Payment> findSuccessfulPaymentsByDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT p FROM Payment p WHERE p.status = 'COMPLETED' AND p.processedAt BETWEEN :startDate AND :endDate")
    Page<Payment> findSuccessfulPaymentsByDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, Pageable pageable);
    
    @Query("SELECT p FROM Payment p WHERE p.status = 'FAILED'")
    List<Payment> findFailedPayments();
    
    @Query("SELECT p FROM Payment p WHERE p.status = 'FAILED'")
    Page<Payment> findFailedPayments(Pageable pageable);
    
    @Query("SELECT p FROM Payment p WHERE p.status IN ('REFUNDED', 'PARTIALLY_REFUNDED')")
    List<Payment> findRefundedPayments();
    
    @Query("SELECT p FROM Payment p WHERE p.status IN ('REFUNDED', 'PARTIALLY_REFUNDED')")
    Page<Payment> findRefundedPayments(Pageable pageable);
    
    @Query("SELECT COUNT(p) FROM Payment p WHERE p.studentId = :studentId")
    long countByStudentId(@Param("studentId") Long studentId);
    
    @Query("SELECT COUNT(p) FROM Payment p WHERE p.courseId = :courseId")
    long countByCourseId(@Param("courseId") Long courseId);
    
    @Query("SELECT COUNT(p) FROM Payment p WHERE p.instructorId = :instructorId")
    long countByInstructorId(@Param("instructorId") Long instructorId);
    
    @Query("SELECT COUNT(p) FROM Payment p WHERE p.status = :status")
    long countByStatus(@Param("status") PaymentStatus status);
    
    @Query("SELECT COUNT(p) FROM Payment p WHERE p.status = 'COMPLETED'")
    long countSuccessfulPayments();
    
    @Query("SELECT COUNT(p) FROM Payment p WHERE p.status = 'FAILED'")
    long countFailedPayments();
    
    @Query("SELECT SUM(p.amount) FROM Payment p WHERE p.studentId = :studentId AND p.status = 'COMPLETED'")
    BigDecimal getTotalAmountByStudent(@Param("studentId") Long studentId);
    
    @Query("SELECT SUM(p.amount) FROM Payment p WHERE p.courseId = :courseId AND p.status = 'COMPLETED'")
    BigDecimal getTotalRevenueByCourse(@Param("courseId") Long courseId);
    
    @Query("SELECT SUM(p.amount) FROM Payment p WHERE p.instructorId = :instructorId AND p.status = 'COMPLETED'")
    BigDecimal getTotalRevenueByInstructor(@Param("instructorId") Long instructorId);
    
    @Query("SELECT SUM(p.amount) FROM Payment p WHERE p.status = 'COMPLETED'")
    BigDecimal getTotalRevenue();
    
    @Query("SELECT SUM(p.refundAmount) FROM Payment p WHERE p.status IN ('REFUNDED', 'PARTIALLY_REFUNDED')")
    BigDecimal getTotalRefundAmount();
    
    @Query("SELECT AVG(p.amount) FROM Payment p WHERE p.status = 'COMPLETED'")
    BigDecimal getAveragePaymentAmount();
    
    @Query("SELECT p.paymentMethod, COUNT(p) FROM Payment p WHERE p.status = 'COMPLETED' GROUP BY p.paymentMethod")
    List<Object[]> getPaymentMethodStatistics();
    
    @Query("SELECT DATE(p.createdAt), SUM(p.amount) FROM Payment p WHERE p.status = 'COMPLETED' AND p.createdAt >= :fromDate GROUP BY DATE(p.createdAt) ORDER BY DATE(p.createdAt)")
    List<Object[]> getDailyRevenue(@Param("fromDate") LocalDateTime fromDate);
    
    @Query("SELECT MONTH(p.createdAt), YEAR(p.createdAt), SUM(p.amount) FROM Payment p WHERE p.status = 'COMPLETED' GROUP BY YEAR(p.createdAt), MONTH(p.createdAt) ORDER BY YEAR(p.createdAt), MONTH(p.createdAt)")
    List<Object[]> getMonthlyRevenue();
}
