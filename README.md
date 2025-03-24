# Quiz App Microservices

This project is a **microservice-based quiz application** built using **Spring Boot**, **Eureka** for service discovery, **OpenFeign** for communication between services, and **Spring Cloud Gateway** for routing. The application allows users to interact with various quiz-related functionalities, such as creating quizzes, fetching quiz questions, and calculating scores.


## Technologies Used

- **Spring Boot**: Framework for building microservices.
- **Spring Cloud Eureka** (Server & Client): For service discovery.
- **Spring Cloud Gateway**: API Gateway for routing requests.
- **OpenFeign**: For communication between microservices.
- **PostgreSQL**: Relational database for storing quiz questions and quiz results.
- **Spring Data JPA**: For database operations.

## Project Overview

This project consists of several microservices that are designed to work together to handle quiz-related tasks:

- **Question Service**: Responsible for CRUD operations on questions and generating quiz questions.
- **Quiz Service**: Manages quizzes, generating questions, and calculating scores.
- **Eureka Server**: Registers services and provides service discovery.
- **API Gateway**: Routes all incoming requests to the appropriate microservices.

## Microservices

### 1. **Question Service**

The **Question Service** manages quiz questions. It includes the following functionalities:

- **CRUD Operations** for quiz questions.
- **Search questions by category**.
- **Generate random questions** for quizzes.
- **Calculate quiz scores** based on user responses.

### 2. **Quiz Service**

The **Quiz Service** handles quiz-related operations:

- **Create a quiz** by generating questions.
- **Retrieve quiz questions** by ID.
- **Calculate quiz results** (score) based on user responses.

#### Example Endpoints:

- `GET /questions`: Retrieve all questions.
- `GET /questions/category/{category}`: Retrieve questions by category.
- `POST /questions`: Save a new question.
- `DELETE /questions/{id}`: Delete a question by its ID.
- `POST /quiz/generate`: Generate random questions for a quiz.
- `POST /quiz/score`: Calculate the score based on user responses.


