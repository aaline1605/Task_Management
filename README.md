# Task Management Application

A **Task Management System** built using **Spring Boot** to handle CRUD operations for tasks. This project leverages **Spring Data JPA**, **Swagger UI**, and **JUnit** for testing. It provides APIs to create, read, update, and delete tasks, ensuring effective task tracking with pagination and robust error handling.

---

## Table of Contents
1. [Features](#features)  
2. [Technologies Used](#technologies-used)  
3. [Project Structure](#project-structure)  
4. [Installation](#installation)  
5. [API Endpoints](#api-endpoints)  
6. [Service Layer Overview](#service-layer-overview)  
7. [Running the Tests](#running-the-tests)  
8. [Usage](#usage)  
9. [Swagger UI](#swagger-ui)  
10. [License](#license)

---

## Features
- **Create, Read, Update, Delete (CRUD) Operations** for tasks  
- **Pagination support** for listing tasks efficiently  
- **Error handling** using `ResponseStatusException`  
- **JUnit tests** for service layer methods  
- **Swagger integration** to explore APIs visually  
- **Custom ID generation logic** for tasks  

---

## Technologies Used
- **Java**  
- **Spring Boot**  
- **Spring Data JPA**  
- **MongoDB**  
- **Swagger UI**  
- **JUnit 5**  
- **Maven**

---

## Project Structure
```
src/main/java
│
├── com.task_management.Task_Management
│   ├── controller        # Controller layer (API handlers)
│   ├── entity            # Entity classes (Database models)
│   ├── repository        # Data access layer (JPA Repositories)
│   └── service           # Service layer (Business logic)
└── test/java
    └── com.task_management.Task_Management
        └── UserServiceTest.java  # JUnit Test cases
```

---

## Installation

### Prerequisites
- Java 17 or higher  
- Maven  
- MongoDB  
- Postman (optional, for API testing)  

### Steps to Set Up
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/task-management.git
   cd task-management
   ```

2. Set up your **database**. For MongoDB:
   ```mongosh
   use task_management;

   ```

3. Update your `application.yml` with database credentials:
   ```yml
   spring:
   data:
    mongodb:
      uri: "mongodb+srv://username:password@cluster0.vh2ap.mongodb.net/task_management" 
   
   ```

4. Build the project:
   ```bash
   mvn clean install
   ```

5. Run the application:
   ```bash
   mvn spring-boot:run
   ```

---

## API Endpoints

### 1. **Create a New Task**
   **POST** `/api/tasks`  
   **Request Body:**
   ```json
   {
     "title": "Complete Documentation",
     "description": "Finish the project documentation",
     "status": "PENDING"
   }
   ```
   **Response:** `201 Created`

### 2. **Get All Tasks (with Pagination)**
   **GET** `/api/tasks?page=0&size=10`  
   **Response:** List of tasks (Paginated)

### 3. **Get Task by ID**
   **GET** `/api/tasks/{id}`  
   **Response:** Task details if found, otherwise `404 Not Found`

### 4. **Update Task by ID**
   **PUT** `/api/tasks/{id}`  
   **Request Body:**
   ```json
   {
     "title": "Updated Task Title",
     "description": "Updated Task Description"
   }
   ```
   **Response:** Updated task details

### 5. **Delete Task by ID**
   **DELETE** `/api/tasks/{id}`  
   **Response:** `204 No Content` if successfully deleted

---

## Service Layer Overview

The **`UserService`** class contains all business logic, including:
- **`generateId()`**: Generates a sequential ID by fetching the latest task entry  
- **`createTask()`**: Creates and saves a new task  
- **`displayAllTask()`**: Retrieves all tasks with pagination  
- **`displayTaskById()`**: Retrieves a task by its ID  
- **`updateTaskById()`**: Updates a task only with provided fields  
- **`deleteTask()`**: Deletes a task by ID with error handling  

---

## Running the Tests

This project includes **JUnit tests** for validating the service layer.

### Running Tests:
1. Open the terminal and navigate to the project directory.
2. Run the following command:
   ```bash
   mvn test
   ```

### Test Cases in `UserServiceTest`:
- **`createTask()`**: Verifies the task creation logic  
- **`displayAllTask()`**: Ensures paginated retrieval of tasks  
- **`displayTaskById()`**: Checks task retrieval by ID  
- **`updateTaskById()`**: Verifies exception handling for task updates  
- **`deleteTask()`**: Confirms deletion logic and exception handling  

---

## Usage

1. Start the application by running:
   ```bash
   mvn spring-boot:run
   ```

2. Use **Postman** or **Swagger UI** to explore and test the APIs.

---

## Swagger UI

After starting the application, Swagger UI can be accessed at:  
**[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)**  

Use this interface to explore the available APIs and try them out.

---

## License

This project is licensed under the **MIT License**. You are free to use, modify, and distribute the code as per the terms of the license.

---

## Conclusion

This **Task Management System** is a lightweight, extensible application that demonstrates how to create RESTful APIs using Spring Boot. With support for **pagination**, **robust error handling**, and **JUnit tests**, it provides a solid foundation for managing tasks effectively.

***HAPPY CODING***
