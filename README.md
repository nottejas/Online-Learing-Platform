# Online Learning Platform

A comprehensive microservices-based e-learning platform built with Spring Boot, inspired by platforms like Udemy. This platform provides a scalable architecture for online course management, user enrollment, payment processing, and course reviews.

## 🚀 Features

### Core Microservices
- **User Service** - User registration, authentication, and profile management
- **Course Service** - Course creation, management, and publishing
- **Enrollment Service** - Student enrollment and progress tracking
- **Payment Service** - Payment processing and transaction management
- **Review Service** - Course reviews and rating system

### Key Functionalities
- ✅ User registration and authentication
- ✅ Role-based access control (Student, Instructor, Admin)
- ✅ Course creation and management
- ✅ Course categorization and difficulty levels
- ✅ Student enrollment and progress tracking
- ✅ Payment processing simulation
- ✅ Course reviews and ratings
- ✅ RESTful API endpoints
- ✅ Database integration with MySQL
- ✅ Security with Spring Security
- ✅ API documentation with Spring Boot Actuator

## 🛠️ Technology Stack

### Backend
- **Java 24** - Programming language
- **Spring Boot 3.5.4** - Application framework
- **Spring Data JPA** - Data persistence
- **Spring Security** - Authentication and authorization
- **Spring Boot Actuator** - Monitoring and management
- **MySQL 8.0** - Primary database
- **Maven** - Build tool

### Additional Technologies (Ready for Integration)
- **Redis** - Caching (disabled for initial testing)
- **Apache Kafka** - Message streaming (disabled for initial testing)
- **Elasticsearch** - Search functionality (disabled for initial testing)
- **Docker** - Containerization (planned)
- **React.js** - Frontend (planned)

## 📁 Project Structure

```
src/main/java/com/example/msaproject/
├── config/                     # Configuration classes
│   └── SecurityConfig.java     # Security configuration
├── userservice/               # User Management Microservice
│   ├── entity/               # User entities and enums
│   ├── repository/           # JPA repositories
│   ├── controller/           # REST controllers
│   ├── service/              # Business logic
│   ├── dto/                  # Data Transfer Objects
│   └── exception/            # Custom exceptions
├── courseservice/            # Course Management Microservice
│   ├── entity/               # Course entities and enums
│   ├── repository/           # JPA repositories
│   ├── controller/           # REST controllers
│   ├── service/              # Business logic
│   ├── dto/                  # Data Transfer Objects
│   └── exception/            # Custom exceptions
├── enrollmentservice/        # Enrollment Management Microservice
│   ├── entity/               # Enrollment entities and enums
│   ├── repository/           # JPA repositories
│   ├── controller/           # REST controllers
│   ├── service/              # Business logic
│   ├── dto/                  # Data Transfer Objects
│   └── exception/            # Custom exceptions
├── paymentservice/           # Payment Processing Microservice
│   ├── entity/               # Payment entities and enums
│   ├── repository/           # JPA repositories
│   └── dto/                  # Data Transfer Objects
└── reviewservice/            # Review and Rating Microservice
    └── entity/               # Review entities and enums
```

## 🚦 Getting Started

### Prerequisites
- Java 24 or higher
- Maven 3.6+
- MySQL 8.0+
- Git

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/nottejas/Online-Learing-Platform.git
   cd Online-Learing-Platform
   ```

2. **Set up MySQL Database**
   ```sql
   CREATE DATABASE elearning_db;
   CREATE USER 'root'@'localhost' IDENTIFIED BY 'root';
   GRANT ALL PRIVILEGES ON elearning_db.* TO 'root'@'localhost';
   FLUSH PRIVILEGES;
   ```

3. **Configure Application Properties**
   Update `src/main/resources/application.properties` with your database credentials if needed.

4. **Build and Run**
   ```bash
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```

5. **Access the Application**
   - Main Application: http://localhost:8080
   - Health Check: http://localhost:8080/actuator/health
   - API Documentation: http://localhost:8080/actuator

## 📚 API Endpoints

### User Service
- `POST /api/users/register` - User registration
- `POST /api/users/login` - User login
- `GET /api/users/{id}` - Get user by ID
- `PUT /api/users/{id}` - Update user profile

### Course Service
- `POST /api/courses` - Create new course
- `GET /api/courses` - Get all courses (paginated)
- `GET /api/courses/{id}` - Get course by ID
- `PUT /api/courses/{id}` - Update course
- `POST /api/courses/{id}/publish` - Publish course
- `GET /api/courses/published` - Get published courses
- `GET /api/courses/search` - Search courses

### Enrollment Service
- `POST /api/enrollments` - Enroll in course
- `GET /api/enrollments/user/{userId}` - Get user enrollments
- `GET /api/enrollments/course/{courseId}` - Get course enrollments
- `PUT /api/enrollments/{id}/progress` - Update progress

### Payment Service
- `POST /api/payments` - Process payment
- `GET /api/payments/user/{userId}` - Get user payments
- `GET /api/payments/{id}` - Get payment details

### Review Service
- `POST /api/reviews` - Create review
- `GET /api/reviews/course/{courseId}` - Get course reviews

## 🧪 Testing with Postman

### Sample Course Creation Request
```json
{
    "title": "Complete Java Programming Masterclass",
    "description": "Learn Java programming from basics to advanced concepts",
    "shortDescription": "Master Java programming with hands-on projects",
    "instructorId": 1,
    "instructorName": "John Doe",
    "category": "PROGRAMMING",
    "level": "BEGINNER",
    "price": 99.99,
    "discountPrice": 79.99,
    "durationHours": 40,
    "durationMinutes": 30,
    "tags": ["Java", "Programming", "OOP", "Backend"],
    "requirements": ["Basic computer knowledge"],
    "learningOutcomes": ["Master Java fundamentals", "Build real-world applications"]
}
```

## 🔧 Configuration

### Available Course Categories
- PROGRAMMING, WEB_DEVELOPMENT, MOBILE_DEVELOPMENT
- DATA_SCIENCE, MACHINE_LEARNING, ARTIFICIAL_INTELLIGENCE
- CYBERSECURITY, CLOUD_COMPUTING, DEVOPS, DATABASE
- UI_UX_DESIGN, GRAPHIC_DESIGN, DIGITAL_MARKETING
- BUSINESS, PROJECT_MANAGEMENT, PHOTOGRAPHY
- VIDEO_EDITING, MUSIC, LANGUAGE, PERSONAL_DEVELOPMENT
- HEALTH_FITNESS, COOKING

### Available Course Levels
- BEGINNER, INTERMEDIATE, ADVANCED, EXPERT, ALL_LEVELS

## 🚧 Development Status

### Completed ✅
- Core microservice architecture
- User management with authentication
- Course management and publishing
- Enrollment system with progress tracking
- Payment processing foundation
- Review system foundation
- Database integration
- Security configuration
- API endpoints
- Clean package organization

### In Progress 🔄
- Payment service completion (controller, service layers)
- Review service completion (controller, service layers)
- API testing and validation

### Planned 📋
- React.js frontend development
- Docker containerization
- Redis caching integration
- Kafka message streaming
- Elasticsearch search functionality
- Rate limiting and monitoring
- ELK stack for logging
- Comprehensive testing suite

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**Tejas** - [GitHub Profile](https://github.com/nottejas)

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- MySQL for reliable database management
- All contributors and supporters of this project

---

⭐ **Star this repository if you find it helpful!** ⭐
