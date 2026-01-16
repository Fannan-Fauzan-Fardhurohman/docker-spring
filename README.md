# Product Management Service

A Spring Boot REST API service for managing products with CRUD operations, implementing Clean Architecture principles and SOLID design patterns.

## 📋 Overview

This project is a simple but well-architected Product Management Service built with Spring Boot 4.0.1 and Java 17. It demonstrates industry best practices including:

- **Clean Architecture** - Separation of concerns across presentation, application, domain, and infrastructure layers
- **SOLID Principles** - Interface Segregation, Dependency Inversion, and Single Responsibility
- **In-Memory Storage** - No database dependency, perfect for learning and testing
- **Global Exception Handling** - Centralized error handling with custom exceptions
- **Validation** - Input validation using Jakarta Bean Validation
- **Structured Responses** - Consistent response format across all endpoints

## 🏗️ Architecture

The project follows a layered architecture pattern:

```
src/main/java/com/dibimbing/materidocker/
├── presentation/          # REST Controllers & Response DTOs
│   ├── controller/
│   └── response/
├── application/           # Service Layer & DTOs
│   ├── service/
│   └── dto/
├── domain/               # Business Entities & Repository Interfaces
│   ├── entity/
│   └── repository/
├── infrastructure/       # Repository Implementations & Exceptions
│   ├── repository/
│   └── exception/
└── config/              # Configuration Classes
```

### Layer Responsibilities

- **Presentation Layer**: HTTP endpoints, request/response handling
- **Application Layer**: Business logic, service interfaces and implementations
- **Domain Layer**: Core business entities and repository contracts
- **Infrastructure Layer**: Technical implementations (in-memory storage, custom exceptions)

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+

### Installation & Running

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd materi-docker
   ```

2. **Build the project**
   ```bash
   ./mvnw clean install
   ```

3. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```

The application will start on `http://localhost:8080`

## 📚 API Documentation

### Base URL
```
http://localhost:8080/api/products
```

### Endpoints

#### 1. Create Product
```http
POST /api/products
Content-Type: application/json

{
  "name": "Laptop",
  "description": "High-performance laptop",
  "price": 15000000,
  "stock": 10
}
```

**Response (201 Created):**
```json
{
  "success": true,
  "message": "Product created successfully",
  "data": {
    "id": "550e8400-e29b-41d4-a716-446655440000",
    "name": "Laptop",
    "description": "High-performance laptop",
    "price": 15000000,
    "stock": 10,
    "createdAt": "2026-01-16T13:58:12",
    "updatedAt": "2026-01-16T13:58:12"
  }
}
```

#### 2. Get Product by ID
```http
GET /api/products/{id}
```

**Response (200 OK):**
```json
{
  "success": true,
  "message": null,
  "data": {
    "id": "550e8400-e29b-41d4-a716-446655440000",
    "name": "Laptop",
    "description": "High-performance laptop",
    "price": 15000000,
    "stock": 10,
    "createdAt": "2026-01-16T13:58:12",
    "updatedAt": "2026-01-16T13:58:12"
  }
}
```

#### 3. Get All Products
```http
GET /api/products
```

**Response (200 OK):**
```json
{
  "success": true,
  "message": "Retrieved 2 products",
  "data": [
    {
      "id": "550e8400-e29b-41d4-a716-446655440000",
      "name": "Laptop",
      "description": "High-performance laptop",
      "price": 15000000,
      "stock": 10,
      "createdAt": "2026-01-16T13:58:12",
      "updatedAt": "2026-01-16T13:58:12"
    }
  ]
}
```

#### 4. Update Product
```http
PUT /api/products/{id}
Content-Type: application/json

{
  "name": "Gaming Laptop",
  "description": "High-performance gaming laptop",
  "price": 18000000,
  "stock": 5
}
```

**Response (200 OK):**
```json
{
  "success": true,
  "message": "Product updated successfully",
  "data": {
    "id": "550e8400-e29b-41d4-a716-446655440000",
    "name": "Gaming Laptop",
    "description": "High-performance gaming laptop",
    "price": 18000000,
    "stock": 5,
    "createdAt": "2026-01-16T13:58:12",
    "updatedAt": "2026-01-16T14:00:00"
  }
}
```

#### 5. Delete Product
```http
DELETE /api/products/{id}
```

**Response (200 OK):**
```json
{
  "success": true,
  "message": "Product deleted successfully",
  "data": null
}
```

### Error Responses

#### Validation Error (400 Bad Request)
```json
{
  "success": false,
  "message": "Validation failed",
  "errors": {
    "name": "must not be blank",
    "price": "must be greater than 0"
  }
}
```

#### Product Not Found (404 Not Found)
```json
{
  "success": false,
  "message": "Product not found with id: 550e8400-e29b-41d4-a716-446655440000"
}
```

#### Product Already Exists (409 Conflict)
```json
{
  "success": false,
  "message": "Product with name 'Laptop' already exists"
}
```

## 🛠️ Technology Stack

- **Java**: 17
- **Spring Boot**: 4.0.1
- **Spring Web MVC**: RESTful API development
- **Spring Validation**: Jakarta Bean Validation
- **Lombok**: Boilerplate code reduction
- **Maven**: Build and dependency management

## 📁 Project Structure

```
materi-docker/
├── src/
│   ├── main/
│   │   ├── java/com/dibimbing/materidocker/
│   │   │   ├── application/
│   │   │   │   ├── dto/
│   │   │   │   │   ├── request/
│   │   │   │   │   │   ├── CreateProductRequest.java
│   │   │   │   │   │   └── UpdateProductRequest.java
│   │   │   │   │   └── response/
│   │   │   │   │       └── ProductResponse.java
│   │   │   │   └── service/
│   │   │   │       ├── ProductService.java
│   │   │   │       └── impl/
│   │   │   │           └── ProductServiceImpl.java
│   │   │   ├── config/
│   │   │   │   └── GlobalExceptionHandler.java
│   │   │   ├── domain/
│   │   │   │   ├── entity/
│   │   │   │   │   └── Product.java
│   │   │   │   └── repository/
│   │   │   │       └── ProductRepository.java
│   │   │   ├── infrastructure/
│   │   │   │   ├── exception/
│   │   │   │   │   ├── ProductAlreadyExistsException.java
│   │   │   │   │   └── ProductNotFoundException.java
│   │   │   │   └── repository/
│   │   │   │       └── InMemoryProductRepository.java
│   │   │   ├── presentation/
│   │   │   │   ├── controller/
│   │   │   │   │   └── ProductController.java
│   │   │   │   └── response/
│   │   │   │       ├── BaseResponse.java
│   │   │   │       ├── ErrorResponse.java
│   │   │   │       └── ValidationErrorResponse.java
│   │   │   └── MateriDockerApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/dibimbing/materidocker/
│           └── MateriDockerApplicationTests.java
├── pom.xml
└── README.md
```

## 🎯 Key Features

### 1. Clean Architecture
- Clear separation between layers
- Dependency Inversion Principle (high-level modules don't depend on low-level modules)
- Domain entities are framework-independent

### 2. SOLID Principles
- **Single Responsibility**: Each class has one reason to change
- **Open/Closed**: Open for extension, closed for modification
- **Liskov Substitution**: Subtypes must be substitutable for their base types
- **Interface Segregation**: Clients shouldn't depend on interfaces they don't use
- **Dependency Inversion**: Depend on abstractions, not concretions

### 3. Exception Handling
- Custom exceptions for business logic errors
- Global exception handler using `@ControllerAdvice`
- Consistent error response format
- Validation error handling with field-level details

### 4. In-Memory Storage
- Thread-safe `ConcurrentHashMap` for data storage
- UUID-based product IDs
- No database setup required

### 5. Request Validation
- Input validation using Jakarta Bean Validation annotations
- Automatic validation error responses
- Field-level error messages

## 🧪 Testing

Run the tests with:
```bash
./mvnw test
```

## 📝 Code Quality

The project demonstrates several best practices:

- **Lombok**: Reduces boilerplate code (@Data, @RequiredArgsConstructor, @Slf4j)
- **Logging**: SLF4J logging in controllers and services
- **Immutability**: DTOs and responses are immutable where appropriate
- **Validation**: Input validation at the presentation layer
- **Error Handling**: Centralized exception handling
- **Clean Code**: Meaningful names, small methods, clear responsibilities

## 🔄 Future Enhancements

- [ ] Add database persistence (PostgreSQL/MySQL)
- [ ] Implement pagination for listing products
- [ ] Add search and filter functionality
- [ ] Implement caching with Redis
- [ ] Add Swagger/OpenAPI documentation
- [ ] Add comprehensive unit and integration tests
- [ ] Implement authentication and authorization
- [ ] Add Docker support

## 👨‍💻 Author

Developed as a learning project for Spring Boot best practices and Clean Architecture.

## 📄 License

This project is for educational purposes.

---

**Happy Coding! 🚀**
