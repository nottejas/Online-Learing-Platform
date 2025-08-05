package com.example.msaproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories
@EnableCaching
@EnableAsync
@EnableTransactionManagement
public class MsaprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsaprojectApplication.class, args);
		System.out.println("\n=== E-Learning Platform Microservices Started Successfully ===");
		System.out.println("Server running on: http://localhost:8080");
		System.out.println("API Documentation: http://localhost:8080/actuator");
		System.out.println("Health Check: http://localhost:8080/actuator/health");
		System.out.println("\nAvailable Microservices:");
		System.out.println("- User Service: /api/users");
		System.out.println("- Course Service: /api/courses");
		System.out.println("- Enrollment Service: /api/enrollments");
		System.out.println("- Payment Service: /api/payments");
		System.out.println("- Review Service: /api/reviews");
		System.out.println("===============================================\n");
	}

}
