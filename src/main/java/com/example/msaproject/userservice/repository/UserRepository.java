package com.example.msaproject.userservice.repository;

import com.example.msaproject.userservice.entity.User;
import com.example.msaproject.userservice.entity.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByUsername(String username);
    
    Optional<User> findByEmail(String email);
    
    Optional<User> findByUsernameOrEmail(String username, String email);
    
    boolean existsByUsername(String username);
    
    boolean existsByEmail(String email);
    
    List<User> findByRole(UserRole role);
    
    List<User> findByIsActive(Boolean isActive);
    
    List<User> findByEmailVerified(Boolean emailVerified);
    
    Page<User> findByRole(UserRole role, Pageable pageable);
    
    Page<User> findByIsActive(Boolean isActive, Pageable pageable);
    
    @Query("SELECT u FROM User u WHERE u.firstName LIKE %:name% OR u.lastName LIKE %:name% OR u.username LIKE %:name%")
    List<User> findByNameContaining(@Param("name") String name);
    
    @Query("SELECT u FROM User u WHERE u.role = :role AND u.isActive = true")
    List<User> findActiveUsersByRole(@Param("role") UserRole role);
    
    @Query("SELECT COUNT(u) FROM User u WHERE u.role = :role")
    long countByRole(@Param("role") UserRole role);
    
    @Query("SELECT COUNT(u) FROM User u WHERE u.isActive = true")
    long countActiveUsers();
}
