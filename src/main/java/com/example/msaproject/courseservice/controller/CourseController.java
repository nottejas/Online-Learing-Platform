package com.example.msaproject.courseservice.controller;

import com.example.msaproject.courseservice.dto.CourseCreateRequest;
import com.example.msaproject.courseservice.dto.CourseResponse;
import com.example.msaproject.courseservice.entity.CourseCategory;
import com.example.msaproject.courseservice.entity.CourseLevel;
import com.example.msaproject.courseservice.service.CourseService;
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
@RequestMapping("/api/courses")
@CrossOrigin(origins = "*")
public class CourseController {
    
    private final CourseService courseService;
    
    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    
    @PostMapping
    public ResponseEntity<CourseResponse> createCourse(@Valid @RequestBody CourseCreateRequest request) {
        CourseResponse course = courseService.createCourse(request);
        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getCourseById(@PathVariable Long id) {
        CourseResponse course = courseService.getCourseById(id);
        return ResponseEntity.ok(course);
    }
    
    @GetMapping
    public ResponseEntity<Page<CourseResponse>> getAllCourses(Pageable pageable) {
        Page<CourseResponse> courses = courseService.getAllCourses(pageable);
        return ResponseEntity.ok(courses);
    }
    
    @GetMapping("/published")
    public ResponseEntity<Page<CourseResponse>> getPublishedCourses(Pageable pageable) {
        Page<CourseResponse> courses = courseService.getPublishedCourses(pageable);
        return ResponseEntity.ok(courses);
    }
    
    @GetMapping("/instructor/{instructorId}")
    public ResponseEntity<Page<CourseResponse>> getCoursesByInstructor(@PathVariable Long instructorId, Pageable pageable) {
        Page<CourseResponse> courses = courseService.getCoursesByInstructor(instructorId, pageable);
        return ResponseEntity.ok(courses);
    }
    
    @GetMapping("/category/{category}")
    public ResponseEntity<Page<CourseResponse>> getCoursesByCategory(@PathVariable CourseCategory category, Pageable pageable) {
        Page<CourseResponse> courses = courseService.getCoursesByCategory(category, pageable);
        return ResponseEntity.ok(courses);
    }
    
    @GetMapping("/level/{level}")
    public ResponseEntity<Page<CourseResponse>> getCoursesByLevel(@PathVariable CourseLevel level, Pageable pageable) {
        Page<CourseResponse> courses = courseService.getCoursesByLevel(level, pageable);
        return ResponseEntity.ok(courses);
    }
    
    @GetMapping("/search")
    public ResponseEntity<Page<CourseResponse>> searchCourses(@RequestParam String keyword, Pageable pageable) {
        Page<CourseResponse> courses = courseService.searchCourses(keyword, pageable);
        return ResponseEntity.ok(courses);
    }
    
    @GetMapping("/search/published")
    public ResponseEntity<Page<CourseResponse>> searchPublishedCourses(@RequestParam String keyword, Pageable pageable) {
        Page<CourseResponse> courses = courseService.searchPublishedCourses(keyword, pageable);
        return ResponseEntity.ok(courses);
    }
    
    @GetMapping("/filter/price")
    public ResponseEntity<Page<CourseResponse>> getCoursesByPriceRange(
            @RequestParam BigDecimal minPrice, 
            @RequestParam BigDecimal maxPrice, 
            Pageable pageable) {
        Page<CourseResponse> courses = courseService.getCoursesByPriceRange(minPrice, maxPrice, pageable);
        return ResponseEntity.ok(courses);
    }
    
    @GetMapping("/filter/rating")
    public ResponseEntity<Page<CourseResponse>> getCoursesByMinimumRating(
            @RequestParam BigDecimal minRating, 
            Pageable pageable) {
        Page<CourseResponse> courses = courseService.getCoursesByMinimumRating(minRating, pageable);
        return ResponseEntity.ok(courses);
    }
    
    @GetMapping("/popular")
    public ResponseEntity<Page<CourseResponse>> getMostPopularCourses(Pageable pageable) {
        Page<CourseResponse> courses = courseService.getMostPopularCourses(pageable);
        return ResponseEntity.ok(courses);
    }
    
    @GetMapping("/top-rated")
    public ResponseEntity<Page<CourseResponse>> getTopRatedCourses(Pageable pageable) {
        Page<CourseResponse> courses = courseService.getTopRatedCourses(pageable);
        return ResponseEntity.ok(courses);
    }
    
    @GetMapping("/newest")
    public ResponseEntity<Page<CourseResponse>> getNewestCourses(Pageable pageable) {
        Page<CourseResponse> courses = courseService.getNewestCourses(pageable);
        return ResponseEntity.ok(courses);
    }
    
    @GetMapping("/featured")
    public ResponseEntity<Page<CourseResponse>> getFeaturedCourses(Pageable pageable) {
        Page<CourseResponse> courses = courseService.getFeaturedCourses(pageable);
        return ResponseEntity.ok(courses);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CourseResponse> updateCourse(@PathVariable Long id, @Valid @RequestBody CourseCreateRequest request) {
        CourseResponse course = courseService.updateCourse(id, request);
        return ResponseEntity.ok(course);
    }
    
    @PutMapping("/{id}/publish")
    public ResponseEntity<CourseResponse> publishCourse(@PathVariable Long id) {
        CourseResponse course = courseService.publishCourse(id);
        return ResponseEntity.ok(course);
    }
    
    @PutMapping("/{id}/unpublish")
    public ResponseEntity<CourseResponse> unpublishCourse(@PathVariable Long id) {
        CourseResponse course = courseService.unpublishCourse(id);
        return ResponseEntity.ok(course);
    }
    
    @PutMapping("/{id}/feature")
    public ResponseEntity<CourseResponse> featureCourse(@PathVariable Long id) {
        CourseResponse course = courseService.featureCourse(id);
        return ResponseEntity.ok(course);
    }
    
    @PutMapping("/{id}/unfeature")
    public ResponseEntity<CourseResponse> unfeatureCourse(@PathVariable Long id) {
        CourseResponse course = courseService.unfeatureCourse(id);
        return ResponseEntity.ok(course);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/stats/count")
    public ResponseEntity<Long> getTotalCoursesCount() {
        long count = courseService.getTotalCoursesCount();
        return ResponseEntity.ok(count);
    }
    
    @GetMapping("/stats/count/published")
    public ResponseEntity<Long> getPublishedCoursesCount() {
        long count = courseService.getPublishedCoursesCount();
        return ResponseEntity.ok(count);
    }
    
    @GetMapping("/stats/count/instructor/{instructorId}")
    public ResponseEntity<Long> getCoursesCountByInstructor(@PathVariable Long instructorId) {
        long count = courseService.getCoursesCountByInstructor(instructorId);
        return ResponseEntity.ok(count);
    }
    
    @GetMapping("/stats/count/category/{category}")
    public ResponseEntity<Long> getCoursesCountByCategory(@PathVariable CourseCategory category) {
        long count = courseService.getCoursesCountByCategory(category);
        return ResponseEntity.ok(count);
    }
    
    @GetMapping("/stats/students/instructor/{instructorId}")
    public ResponseEntity<Long> getTotalStudentsByInstructor(@PathVariable Long instructorId) {
        Long totalStudents = courseService.getTotalStudentsByInstructor(instructorId);
        return ResponseEntity.ok(totalStudents != null ? totalStudents : 0L);
    }
}
