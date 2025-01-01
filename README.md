
# Task Management API ⌛

This is a Task Management API built using Spring Boot. It provides endpoints for managing tasks, including creating, reading, updating, and deleting tasks. Tasks can also be filtered and sorted based on various parameters.

![](https://projectsly.com/images/task-management-system-screenshot-1.png?v=1691124479409199525)
---

## HTTP Methods and Their Endpoints

### **GET**

Used to retrieve data from the server.

- **GET `/tasks`**  
  Retrieve all tasks with optional sorting.  
  **Query Parameters**:  
  - `sortBy` (optional): Field to sort by (e.g., `title`, `status`).  
  - `ascending` (optional, boolean): Sort order (`true` for ascending, `false` for descending`).  
  **Response**: List of all tasks.

- **GET `/tasks/{id}`**  
  Retrieve a specific task by its ID.  
  **Path Variable**:  
  - `id` (integer): Task ID.  
  **Response**: Details of the task with the specified ID.

- **GET `/tasks/title/{title}`**  
  Retrieve tasks containing the specified title.  
  **Path Variable**:  
  - `title` (string): Substring of the task title.  
  **Response**: List of tasks containing the title.

- **GET `/tasks/done`**  
  Retrieve all completed tasks.  
  **Response**: List of completed tasks.

- **GET `/tasks/not_done`**  
  Retrieve all incomplete tasks.  
  **Response**: List of incomplete tasks.


### **POST**

Used to create a new resource on the server.

- **POST `/tasks`**  
  Create a new task.  
  **Request Body**:  
  ```json
  {
    "title": "Task Title",
    "description": "Task Description"
  }
  ```  
  **Response**: The newly created task.


### **PUT**

Used to update or modify an existing resource.

- **PUT `/tasks/complete/{id}`**  
  Mark a task as completed.  
  **Path Variable**:  
  - `id` (integer): Task ID.  
  **Response**: The updated task marked as complete.

- **PUT `/tasks/undo/{id}`**  
  Mark a task as incomplete.  
  **Path Variable**:  
  - `id` (integer): Task ID.  
  **Response**: The updated task marked as incomplete.

- **PUT `/tasks`**  
  Update an existing task.  
  **Request Body**:  
  ```json
  {
    "id": 1,
    "title": "Updated Task Title",
    "description": "Updated Task Description"
    ...
  }
  ```  
  **Response**: The updated task.


### **DELETE**

Used to delete resources on the server.

- **DELETE `/tasks/{id}`**  
  Delete a specific task by its ID.  
  **Path Variable**:  
  - `id` (integer): Task ID.  
  **Response**: Details of the deleted task.

- **DELETE `/tasks/all`**  
  Bulk delete tasks based on their completion status.  
  **Query Parameter**:  
  - `which` (optional): Specify `done` to delete completed tasks or `not_done` to delete incomplete tasks.  
  **Response**: List of deleted tasks.

---

## Technologies Used

- **Spring Boot**: Framework for building the RESTful API.
- **Java**: Backend language.
- **Spring Data JPA**: Database interaction.
- **Hibernate Validator**: Validation for request payloads.

---

## How to Run

1. Clone the repository.
2. Open the project in your IDE.
3. Configure the database connection in `application.properties`.
4. Run the application using the `main` method in the `TaskManagementApplication` class.
5. Use tools like Postman or curl to test the API endpoints.
---
## Contact
For any further queries, feel free to reach out to me via 📩 [email](mainakcr72002@gmail.com).

