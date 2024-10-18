# Employee Management System API

## Overview
The Employee Management System API is a Spring Boot application designed to manage employee records. It provides endpoints for CRUD operations on employee data, supporting features such as pagination and basic authentication.

## Key Features
- **CRUD Operations**: Create, Read, Update, and Delete employee records.
- **Pagination**: Retrieve employees with pagination support, allowing for efficient data retrieval.
- **Validation**: Input validation using Spring's validation framework to ensure data integrity.
- **Basic Authentication**: Secure API endpoints using HTTP Basic Authentication.
- **Swagger Documentation**: API documentation generated with Swagger for easy reference and testing.

## API Endpoints

### 1. Create Employee
- **Endpoint**: `POST /api/employees`
- **Request Body**:
  ```json
  {
    "firstName": "Godfrey",
    "lastName": "Kivaru",
    "email": "kivarugodfrey@gmail.com",
    "department": "Engineering",
    "salary": 2500000.00
  }
  ```
- **Response**: `201 Created` with the created employee details.

### 2. Get All Employees
- **Endpoint**: `GET /api/employees`
- **Query Parameters**: Supports pagination with `page` and `size` parameters.
- **Response**: `200 OK` with a paginated list of employees.

### 3. Get Employee by ID
- **Endpoint**: `GET /api/employees/{id}`
- **Response**: `200 OK` with the employee details or `404 Not Found` if not found.

### 4. Update Employee
- **Endpoint**: `PUT /api/employees/{id}`
- **Request Body**: Same as Create Employee.
- **Response**: `200 OK` with the updated employee details or `404 Not Found` if not found.

### 5. Delete Employee
- **Endpoint**: `DELETE /api/employees/{id}`
- **Response**: `204 No Content` if successfully deleted or `404 Not Found` if not found.

## Project Structure
```
/src
 ├── main
 │   ├── java
 │   │   └── com
 │   │       └── godfreykivaru
 │   │           └── ems
 │   │               ├── config
 │   │               ├── controller
 │   │               ├── entity
 │   │               ├── exception
 │   │               ├── repository
 │   │               ├── service
 │   │               └── EmployeeManagementSystemApplication.java
 │   └── resources
 │       └── application.properties
 └── test
     └── java
         └── com
             └── godfreykivaru
                 └── ems
                     └── EmployeeControllerIntegrationTest.java
                     └── EmployeeServiceTest.java
                     └── EmsApplicationTests.java
```

## Challenges and Issues Encountered
- **Integration Testing**: Ensuring that the integration tests correctly interacted with the in-memory database and provided the expected results.
- **Pagination**: Initially returning a list instead of a pageable response required adjustments in both the service and controller layers.
- **Validation**: Configuring validation annotations to ensure proper validation of employee fields led to debugging issues with unsupported types.

## External Libraries and Plugins
- **Spring Boot**: Main framework for building the application.
- **Spring Data JPA**: For data persistence and repository management.
- **H2 Database**: In-memory database used for testing purposes.
- **Spring Validation**: For input validation.
- **Spring Security**: For implementing basic authentication.
- **Swagger**: For API documentation and testing.
- **JUnit and Mockito**: For unit testing and mocking dependencies.
- **Lombok**: For reducing boilerplate code, such as getters, setters, and constructors.

## Getting Started
1. Clone the repository.
2. Navigate to the project directory.
3. Run the application using:
   ```bash
   ./mvnw spring-boot:run
   ```
4. Access the API documentation at `/swagger-ui.html` after running the application.

## License
This project is licensed under the MIT License.
