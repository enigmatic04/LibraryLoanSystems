# Library Loan Management System

## Project Overview

The Library Loan Management System is a console-based JDBC application developed using Java and Apache Derby. 

The project demonstrates core database management concepts including CRUD operations, transaction management, rollback handling, savepoints, prepared statements, and JDBC performance benchmarking.

This system simulates a real-world library workflow where members can borrow and return books while maintaining database consistency using ACID transactions.

---

## Features

- Add Members
- Add Books
- Process Book Loans
- Return Books
- View All Books
- View Members
- View Active Loans
- Transaction Management using JDBC
- Rollback & Savepoint Support
- PreparedStatement-based Queries
- Batch Insert Benchmarking
- Menu-driven Command Line Interface
- Apache Derby Embedded Database Integration

---

## Technologies Used

- Java
- JDBC
- Apache Derby
- VS Code
- Git & GitHub

---

## Project Structure

```text
LibraryLoanSystems
│
├── lib
│   ├── derby.jar
│   ├── derbyshared.jar
│   └── derbytools.jar
│
├── src
│   ├── benchmark
│   │     PerformanceEvaluator.java
│   │
│   ├── connection
│   │     ConnectionManager.java
│   │
│   ├── service
│   │     BusinessLogic.java
│   │     TransactionService.java
│   │
│   ├── util
│   │     DBInitializer.java
│   │
│   └── MainApp.java
│
└── README.md