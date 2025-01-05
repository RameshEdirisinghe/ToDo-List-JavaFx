# ToDo List

Welcome to **ToDo List TaskFlow**, a multi-user task management application designed to simplify task tracking and enhance productivity. Built with **JavaFX**, **JDBC**, and **OOP** principles, this project offers a robust, secure, and user-friendly experience.

## Key Features
- **Dynamic User Interface**: A sleek and interactive GUI using JavaFX for a smooth task management experience.
- **Multi-User Support**: Individual user accounts with personalized to-do lists.
- **Secure Data Handling**: User login with data persistence and security.
- **Efficient Task Management**: Add, update, delete, and organize tasks with ease.

## Technologies Used
- **JavaFX**: For building a responsive and modern graphical user interface.
- **JDBC (Java Database Connectivity)**: For secure and efficient database connectivity.
- **OOP (Object-Oriented Programming)**: Applied design principles like encapsulation, inheritance, and polymorphism.

## Getting Started
### Prerequisites
- Java Development Kit (JDK) 8 or higher
- JavaFX SDK
- MySQL or any relational database

### Installation
1. Clone the repository:
   ```
   git clone https://github.com/RameshEdirisinghe/ToDo-List-JavaFx.git
   ```
2. Set up your database:
   - Create a database and update the connection configuration in the source code.
3. Open the project in your preferred IDE (such as IntelliJ IDEA or Eclipse).
4. Build and run the application.

### Database Configuration
Update the `DBConnection.java` file with your database credentials:
```java
private static final String URL = "jdbc:mysql://localhost:3306/your_database";
private static final String USER = "your_username";
private static final String PASSWORD = "your_password";
```

## Usage
- **User Registration and Login**: Create an account to access personalized to-do lists.
- **Task Management**: Add, edit, mark as done, or delete tasks.
- **Data Persistence**: All tasks are stored securely in the database.

## Screenshots
![Login Screen](screenshots/login_screen.png)
![Main To-Do List](screenshots/todo_list_screen.png)
![Task Editing](screenshots/edit_task_screen.png)

## Contributing
Contributions are welcome! Please fork this repository and submit a pull request.

## License
This project is licensed under the MIT License.

<hr>
