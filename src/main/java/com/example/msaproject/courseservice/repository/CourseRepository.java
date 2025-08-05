package com.example.msaproject.courseservice.repository;

import com.example.msaproject.courseservice.entity.Course;
import com.example.msaproject.courseservice.entity.CourseCategory;
import com.example.msaproject.courseservice.entity.CourseLevel;
import com.example.msaproject.courseservice.entity.CourseStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;


@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    
    List<Course> findByInstructorId(Long instructorId);
    
    Page<Course> findByInstructorId(Long instructorId, Pageable pageable);
    
    List<Course> findByCategory(CourseCategory category);
    
    Page<Course> findByCategory(CourseCategory category, Pageable pageable);
    
    List<Course> findByLevel(CourseLevel level);
    
    Page<Course> findByLevel(CourseLevel level, Pageable pageable);
    
    List<Course> findByStatus(CourseStatus status);
    
    Page<Course> findByStatus(CourseStatus status, Pageable pageable);
    
    List<Course> findByIsPublished(Boolean isPublished);
    
    Page<Course> findByIsPublished(Boolean isPublished, Pageable pageable);
    
    List<Course> findByIsFeatured(Boolean isFeatured);
    
    Page<Course> findByIsFeatured(Boolean isFeatured, Pageable pageable);
    
    @Query("SELECT c FROM Course c WHERE c.isPublished = true AND c.status = 'PUBLISHED'")
    List<Course> findPublishedCourses();
    
    @Query("SELECT c FROM Course c WHERE c.isPublished = true AND c.status = 'PUBLISHED'")
    Page<Course> findPublishedCourses(Pageable pageable);
    
    @Query("SELECT c FROM Course c WHERE c.title LIKE %:keyword% OR c.description LIKE %:keyword% OR c.shortDescription LIKE %:keyword%")
    List<Course> searchByKeyword(@Param("keyword") String keyword);
    
    @Query("SELECT c FROM Course c WHERE c.title LIKE %:keyword% OR c.description LIKE %:keyword% OR c.shortDescription LIKE %:keyword%")
    Page<Course> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);
    
    @Query("SELECT c FROM Course c WHERE c.isPublished = true AND c.status = 'PUBLISHED' AND " +
           "(c.title LIKE %:keyword% OR c.description LIKE %:keyword% OR c.shortDescription LIKE %:keyword%)")
    Page<Course> searchPublishedCourses(@Param("keyword") String keyword, Pageable pageable);
    
    @Query("SELECT c FROM Course c WHERE c.price BETWEEN :minPrice AND :maxPrice")
    List<Course> findByPriceRange(@Param("minPrice") BigDecimal minPrice, @Param("maxPrice") BigDecimal maxPrice);
    
    @Query("SELECT c FROM Course c WHERE c.price BETWEEN :minPrice AND :maxPrice")
    Page<Course> findByPriceRange(@Param("minPrice") BigDecimal minPrice, @Param("maxPrice") BigDecimal maxPrice, Pageable pageable);
    
    @Query("SELECT c FROM Course c WHERE c.averageRating >= :minRating")
    List<Course> findByMinimumRating(@Param("minRating") BigDecimal minRating);
    
    @Query("SELECT c FROM Course c WHERE c.averageRating >= :minRating")
    Page<Course> findByMinimumRating(@Param("minRating") BigDecimal minRating, Pageable pageable);
    
    @Query("SELECT c FROM Course c WHERE c.isPublished = true AND c.status = 'PUBLISHED' AND c.category = :category")
    Page<Course> findPublishedCoursesByCategory(@Param("category") CourseCategory category, Pageable pageable);
    
    @Query("SELECT c FROM Course c WHERE c.isPublished = true AND c.status = 'PUBLISHED' AND c.level = :level")
    Page<Course> findPublishedCoursesByLevel(@Param("level") CourseLevel level, Pageable pageable);
    
    @Query("SELECT c FROM Course c WHERE c.isPublished = true AND c.status = 'PUBLISHED' " +
           "ORDER BY c.totalStudents DESC")
    Page<Course> findMostPopularCourses(Pageable pageable);
    
    @Query("SELECT c FROM Course c WHERE c.isPublished = true AND c.status = 'PUBLISHED' " +
           "ORDER BY c.averageRating DESC, c.totalReviews DESC")
    Page<Course> findTopRatedCourses(Pageable pageable);
    
    @Query("SELECT c FROM Course c WHERE c.isPublished = true AND c.status = 'PUBLISHED' " +
           "ORDER BY c.createdAt DESC")
    Page<Course> findNewestCourses(Pageable pageable);
    
    @Query("SELECT COUNT(c) FROM Course c WHERE c.instructorId = :instructorId")
    long countByInstructorId(@Param("instructorId") Long instructorId);
    
    @Query("SELECT COUNT(c) FROM Course c WHERE c.category = :category")
    long countByCategory(@Param("category") CourseCategory category);
    
    @Query("SELECT COUNT(c) FROM Course c WHERE c.isPublished = true AND c.status = 'PUBLISHED'")
    long countPublishedCourses();
    
    @Query("SELECT SUM(c.totalStudents) FROM Course c WHERE c.instructorId = :instructorId")
    Long getTotalStudentsByInstructor(@Param("instructorId") Long instructorId);
}
