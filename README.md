# Question Platform Backend

## Description

This project is a backend service for a question-asking platform that allows users to create and manage different types of questions, such as **Polls** and **Trivia**. Users can also submit answers (votes) for these questions, and the platform tracks the results.

---

## Tools and Project Structure

This project is built using **Spring Boot**, a popular framework for building scalable backend applications in Java. The main tools and layers used in this project are:

- **Spring Boot**: Provides a robust foundation for creating a RESTful API and handling dependency injection.
- **Spring Data JPA**: Simplifies interaction with the database through a repository pattern.
- **H2 Database**: An in-memory database for rapid development and testing, avoiding external dependencies.

### Project Layers

1. **Controller Layer**: Handles HTTP requests and routes them to the appropriate service methods. 
2. **Service Layer**: Contains business logic, including methods for creating questions, fetching question details, and processing votes.
3. **Repository Layer**: Uses Spring Data JPA to handle database operations for entities like `Question`, `Answer`, and `Vote`.

---

## Why H2 Database?

The **H2 Database** is used in this project primarily for ease of development. It’s an in-memory database, which means it doesn’t require installation or setup, and data is automatically cleared when the application stops. This setup is ideal for prototyping and rapid testing. 

In a production environment, a more persistent database such as **MySQL** or **PostgreSQL** would be preferable to ensure data consistency across sessions.

---

## Future Improvements

If I had more time to work on this project, I would add the following features:

1. **API Documentation with jDocs**: Generate a detailed API reference using jDocs to improve developer understanding and usability.
2. **Advanced Error Handling**: Implement comprehensive error handling to provide clear feedback for various error cases (e.g., invalid input, non-existent question IDs).
3. **Strategy Design Pattern for Questions**: Implement the Strategy design pattern for different question types, making the system **open for extension** and **closed for modification**. This would allow for easily adding new question types without altering existing code.

4. ## Running the Project and Testing Locally

### How to Run the Project

1. **Clone the repository**:
   ```bash
   git clone https://github.com/arashalif/QuestionPlatform.git
   cd question-platform 
  
2. **Build and Run:**
   
     **Using IntelliJ IDEA:**
       Open the project in IntelliJ IDEA. Run the QuestionPlatformApplication main class.
   
      **Or with Command Line:**
   ```bash
   ./gradlew bootRun
   
3. **Access the Application::**
   - The application will start on http://localhost:8080.
   - Access the H2 Console for database inspection at http://localhost:8080/h2-console with the following credentials:
        - **JDBC URL:** jdbc:h2:mem:testdb
        - **Username:** sa
        - **Password:** password

## Testing the Project Locally with Sample Data

### Create a New Question:

**Endpoint**: `POST /questions/insert`

**Sample Request Body**:
  ```json
 {
      "text": "What is the capital of France?",
      "type": "Trivia",
      "answers": ["Paris", "London", "Berlin", "Madrid"],
      "correctAnswer": "Paris"
  }
```

### Get a Question by ID:

**Endpoint**: `GET /questions/{id}`  
Replace `{id}` with the question ID you received from the create question response.

### Submit a Vote:

**Endpoint**: `POST /questions/vote`

**Sample Request Body**:
```json
{
    "questionId": 1,
    "answer": "Paris"
}


