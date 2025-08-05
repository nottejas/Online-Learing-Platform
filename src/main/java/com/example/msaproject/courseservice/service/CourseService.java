package com.example.msaproject.courseservice.service;

import com.example.msaproject.courseservice.dto.CourseCreateRequest;
import com.example.msaproject.courseservice.dto.CourseResponse;
import com.example.msaproject.courseservice.entity.Course;
import com.example.msaproject.courseservice.entity.CourseCategory;
import com.example.msaproject.courseservice.entity.CourseLevel;
import com.example.msaproject.courseservice.entity.CourseStatus;
import com.example.msaproject.courseservice.exception.CourseNotFoundException;
import com.example.msaproject.courseservice.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@Transactional
public class CourseService {
    
    private final CourseRepository courseRepository;
    
    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    
    public CourseResponse createCourse(CourseCreateRequest request) {
        Course course = new Course();
        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setShortDescription(request.getShortDescription());
        course.setInstructorId(request.getInstructorId());
        course.setInstructorName(request.getInstructorName());
        course.setCategory(request.getCategory());
        course.setLevel(request.getLevel());
        course.setPrice(request.getPrice());
        course.setDiscountPrice(request.getDiscountPrice());
        course.setThumbnailUrl(request.getThumbnailUrl());
        course.setPreviewVideoUrl(request.getPreviewVideoUrl());
        course.setDurationHours(request.getDurationHours());
        course.setDurationMinutes(request.getDurationMinutes());
        course.setTags(request.getTags() != null ? request.getTags() : new ArrayList<>());
        course.setRequirements(request.getRequirements() != null ? request.getRequirements() : new ArrayList<>());
        course.setLearningOutcomes(request.getLearningOutcomes() != null ? request.getLearningOutcomes() : new ArrayList<>());
        
        Course savedCourse = courseRepository.save(course);
        return convertToResponse(savedCourse);
    }
    
    @Transactional(readOnly = true)
    public CourseResponse getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id: " + id));
        return convertToResponse(course);
    }
    
    @Transactional(readOnly = true)
    public Page<CourseResponse> getAllCourses(Pageable pageable) {
        Page<Course> courses = courseRepository.findAll(pageable);
        return courses.map(this::convertToResponse);
    }
    
    @Transactional(readOnly = true)
    public Page<CourseResponse> getPublishedCourses(Pageable pageable) {
        Page<Course> courses = courseRepository.findPublishedCourses(pageable);
        return courses.map(this::convertToResponse);
    }
    
    @Transactional(readOnly = true)
    public Page<CourseResponse> getCoursesByInstructor(Long instructorId, Pageable pageable) {
        Page<Course> courses = courseRepository.findByInstructorId(instructorId, pageable);
        return courses.map(this::convertToResponse);
    }
    
    @Transactional(readOnly = true)
    public Page<CourseResponse> getCoursesByCategory(CourseCategory category, Pageable pageable) {
        Page<Course> courses = courseRepository.findPublishedCoursesByCategory(category, pageable);
        return courses.map(this::convertToResponse);
    }
    
    @Transactional(readOnly = true)
    public Page<CourseResponse> getCoursesByLevel(CourseLevel level, Pageable pageable) {
        Page<Course> courses = courseRepository.findPublishedCoursesByLevel(level, pageable);
        return courses.map(this::convertToResponse);
    }
    
    @Transactional(readOnly = true)
    public Page<CourseResponse> searchCourses(String keyword, Pageable pageable) {
        Page<Course> courses = courseRepository.searchByKeyword(keyword, pageable);
        return courses.map(this::convertToResponse);
    }
    
    @Transactional(readOnly = true)
    public Page<CourseResponse> searchPublishedCourses(String keyword, Pageable pageable) {
        Page<Course> courses = courseRepository.searchPublishedCourses(keyword, pageable);
        return courses.map(this::convertToResponse);
    }
    
    @Transactional(readOnly = true)
    public Page<CourseResponse> getCoursesByPriceRange(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable) {
        Page<Course> courses = courseRepository.findByPriceRange(minPrice, maxPrice, pageable);
        return courses.map(this::convertToResponse);
    }
    
    @Transactional(readOnly = true)
    public Page<CourseResponse> getCoursesByMinimumRating(BigDecimal minRating, Pageable pageable) {
        Page<Course> courses = courseRepository.findByMinimumRating(minRating, pageable);
        return courses.map(this::convertToResponse);
    }
    
    @Transactional(readOnly = true)
    public Page<CourseResponse> getMostPopularCourses(Pageable pageable) {
        Page<Course> courses = courseRepository.findMostPopularCourses(pageable);
        return courses.map(this::convertToResponse);
    }
    
    @Transactional(readOnly = true)
    public Page<CourseResponse> getTopRatedCourses(Pageable pageable) {
        Page<Course> courses = courseRepository.findTopRatedCourses(pageable);
        return courses.map(this::convertToResponse);
    }
    
    @Transactional(readOnly = true)
    public Page<CourseResponse> getNewestCourses(Pageable pageable) {
        Page<Course> courses = courseRepository.findNewestCourses(pageable);
        return courses.map(this::convertToResponse);
    }
    
    @Transactional(readOnly = true)
    public Page<CourseResponse> getFeaturedCourses(Pageable pageable) {
        Page<Course> courses = courseRepository.findByIsFeatured(true, pageable);
        return courses.map(this::convertToResponse);
    }
    
    public CourseResponse updateCourse(Long id, CourseCreateRequest request) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id: " + id));
        
        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setShortDescription(request.getShortDescription());
        course.setCategory(request.getCategory());
        course.setLevel(request.getLevel());
        course.setPrice(request.getPrice());
        course.setDiscountPrice(request.getDiscountPrice());
        course.setThumbnailUrl(request.getThumbnailUrl());
        course.setPreviewVideoUrl(request.getPreviewVideoUrl());
        course.setDurationHours(request.getDurationHours());
        course.setDurationMinutes(request.getDurationMinutes());
        
        if (request.getTags() != null) {
            course.setTags(request.getTags());
        }
        if (request.getRequirements() != null) {
            course.setRequirements(request.getRequirements());
        }
        if (request.getLearningOutcomes() != null) {
            course.setLearningOutcomes(request.getLearningOutcomes());
        }
        
        Course updatedCourse = courseRepository.save(course);
        return convertToResponse(updatedCourse);
    }
    
    public CourseResponse publishCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id: " + id));
        
        course.setIsPublished(true);
        course.setStatus(CourseStatus.PUBLISHED);
        course.setPublishedAt(LocalDateTime.now());
        
        Course updatedCourse = courseRepository.save(course);
        return convertToResponse(updatedCourse);
    }
    
    public CourseResponse unpublishCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id: " + id));
        
        course.setIsPublished(false);
        course.setStatus(CourseStatus.DRAFT);
        course.setPublishedAt(null);
        
        Course updatedCourse = courseRepository.save(course);
        return convertToResponse(updatedCourse);
    }
    
    public CourseResponse featureCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id: " + id));
        
        course.setIsFeatured(true);
        
        Course updatedCourse = courseRepository.save(course);
        return convertToResponse(updatedCourse);
    }
    
    public CourseResponse unfeatureCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id: " + id));
        
        course.setIsFeatured(false);
        
        Course updatedCourse = courseRepository.save(course);
        return convertToResponse(updatedCourse);
    }
    
    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new CourseNotFoundException("Course not found with id: " + id);
        }
        courseRepository.deleteById(id);
    }
    
    @Transactional(readOnly = true)
    public long getTotalCoursesCount() {
        return courseRepository.count();
    }
    
    @Transactional(readOnly = true)
    public long getPublishedCoursesCount() {
        return courseRepository.countPublishedCourses();
    }
    
    @Transactional(readOnly = true)
    public long getCoursesCountByInstructor(Long instructorId) {
        return courseRepository.countByInstructorId(instructorId);
    }
    
    @Transactional(readOnly = true)
    public long getCoursesCountByCategory(CourseCategory category) {
        return courseRepository.countByCategory(category);
    }
    
    @Transactional(readOnly = true)
    public Long getTotalStudentsByInstructor(Long instructorId) {
        return courseRepository.getTotalStudentsByInstructor(instructorId);
    }
    
    private CourseResponse convertToResponse(Course course) {
        CourseResponse response = new CourseResponse();
        response.setId(course.getId());
        response.setTitle(course.getTitle());
        response.setDescription(course.getDescription());
        response.setShortDescription(course.getShortDescription());
        response.setInstructorId(course.getInstructorId());
        response.setInstructorName(course.getInstructorName());
        response.setCategory(course.getCategory());
        response.setLevel(course.getLevel());
        response.setPrice(course.getPrice());
        response.setDiscountPrice(course.getDiscountPrice());
        response.setThumbnailUrl(course.getThumbnailUrl());
        response.setPreviewVideoUrl(course.getPreviewVideoUrl());
        response.setDurationHours(course.getDurationHours());
        response.setDurationMinutes(course.getDurationMinutes());
        response.setTotalLectures(course.getTotalLectures());
        response.setTotalStudents(course.getTotalStudents());
        response.setAverageRating(course.getAverageRating());
        response.setTotalReviews(course.getTotalReviews());
        response.setTags(course.getTags());
        response.setRequirements(course.getRequirements());
        response.setLearningOutcomes(course.getLearningOutcomes());
        response.setStatus(course.getStatus());
        response.setIsPublished(course.getIsPublished());
        response.setIsFeatured(course.getIsFeatured());
        response.setPublishedAt(course.getPublishedAt());
        response.setCreatedAt(course.getCreatedAt());
        response.setUpdatedAt(course.getUpdatedAt());
        return response;
    }
}
