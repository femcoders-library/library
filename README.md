# 📚 Library Manager

- [Library Manager](#library-manager)
    - [About the project](#about-the-project)
    - [Features](#features)
    - [Interface](#interface)
    - [Technical requirements](#technical-requirements)
        - [Functional](#functional)
        - [Non-functional](#non-functional)
    - [Tech stack and environment](#tech-stack-and-environment)
        - [Tools](#tools)
        - [Programming language](#programming-language)
        - [Architecture](#architecture)
    - [Testing](#testing)
    - [Installation](#installation)
    - [Next steps](#next-steps)
    - [Authors](#authors)


---

## About the project

This is a terminal-based Java application designed to manage a collection of books in a library. The system allows users to **view, add, update, delete, and search** books in a MySQL database using a simple console menu.

The project follows the **MVC (Model-View-Controller)** architecture. The goal is to provide a solid foundation for learning full-stack Java development with persistent data and clean structure.

---

## Features

- View all books
- Add new books
- Update book details (full or by specific fields)
- Delete books
- Search books by title or author

Each book includes:
- Title
- Synopsis
- ISBN (unique)
- Author(s)
- Genre(s)

---

## Interface

Terminal-based interface displaying:
- Main menu with numbered options
- Forms to input book data
- Formatted book listings
- Validation messages

---

## Technical requirements

### Functional

- Display main menu with numbered options
- Input book data through console
- Validate ISBN uniqueness
- Update entire book or individual fields
- Search books by title or author (partial match optional)
- CRUD operations connected to a real MySQL database

### Non-functional

- Follow Java best practices
- Organized codebase following MVC architecture
- Read database credentials from `.env` file using `dotenv-java`
- Exception handling for database errors

---

## Tech stack and environment

### Tools
![IntelliJ](https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)

### Programming language
Java 21

### Architecture

- Model: `Book`
- View: `BookView`
- Controller: `BookController`
- Repository: `BookRepository`
- Database configuration: `DBManager`
- Database: MySQL

---

## Testing

Unit tests are implemented using **JUnit 5** and **Mockito**:

- ✅ `BookControllerTest` covers all controller methods with mocked repository
- ✅ `BookRepositoryTest` covers database operations with real/test DB
- ✅ `BookViewTest` verifies interaction with the controller using mocked input
---

## Installation

> Java 21 and MySQL must be installed.

1. Clone this repository.
2. Create a `.env` file in the project root or `src/main/resources`:
   ```env
   DB_URL=jdbc:mysql://localhost:3306/your_db_name
   DB_USER=your_user
   DB_PASSWORD=your_password
   ```
3. Import the project into IntelliJ IDEA.
4. Create the books table in your MySQL database:
    ```sql
    CREATE TABLE `books` (
      `id` int NOT NULL AUTO_INCREMENT,
      `title` varchar(45) NOT NULL,
      `synopsis` varchar(200) NOT NULL,
      `isbn` varchar(45) NOT NULL,
      `author` varchar(150) NOT NULL,
      `genre` varchar(150) NOT NULL,
      PRIMARY KEY (`id`),
      UNIQUE KEY `isbn_UNIQUE` (`isbn`),
      UNIQUE KEY `id_UNIQUE` (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
    ```
5. Run Main.java to launch the terminal interface.

---

## Next steps

- Add a Dockerfile to containerize the app
- Connect to CI/CD with Jenkins

---

## Authors
- [Anna Nepyivoda](https://github.com/NepyAnna)
- [Vitaliia Rubanenko](http://github.com/vitaFlash)
- [Morena Peralta](https://github.com/More-Pe)
- [Débora Rubio](https://github.com/debsrdev)