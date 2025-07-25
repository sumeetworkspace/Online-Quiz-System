# Online Quiz System (Java Swing + MySQL)

A desktop-based quiz application built using **Java Swing** and **MySQL**.

## Features
- User Registration & Login
- Admin can insert questions
- Quiz selection and attempt
- Result calculation and display

## Tech Stack
- Java (Swing for UI)
- MySQL (Database)
- JDBC (Database Connection)

## How to Run
1. Import the project into any Java IDE (NetBeans / Eclipse / IntelliJ).
2. Create a database in MySQL and run the script `quiz_system.sql`.
3. Update `DBConnection.java` with your database username and password.
4. Run `OnlineQuizSystem.java` to start the application.

## Database
Import the `quiz_system.sql` file into your MySQL server.

**Author:** Sumeet Atmaram Jadhav


``sql
CREATE DATABASE quiz_system;
USE quiz_system;

CREATE TABLE users (
  user_id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(100) UNIQUE NOT NULL,
  password VARCHAR(100) NOT NULL
);

CREATE TABLE quizzes (
  id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(200) NOT NULL
);

CREATE TABLE questions (
  id INT AUTO_INCREMENT PRIMARY KEY,
  quiz_id INT,
  question TEXT,
  option_a VARCHAR(200),
  option_b VARCHAR(200),
  option_c VARCHAR(200),
  option_d VARCHAR(200),
  correct_option CHAR(1),
  FOREIGN KEY (quiz_id) REFERENCES quizzes(id)
);

CREATE TABLE results (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_id INT,
  quiz_id INT,
  score INT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);