# 🏢 Employee Leave Management System - REST API

## 📌 Overview

Employee Leave Management System is a RESTful backend application built using Spring Boot and Spring Data JPA.

The system provides CRUD-based API endpoints for managing employee leave requests and tracking workflow status.

All endpoints were tested using Postman.

This project focuses on backend API design, database integration, and layered architecture implementation.

---

## 🛠 Tech Stack

- Java
- Spring Boot
- Spring Data JPA (Hibernate)
- MySQL / Oracle
- Maven
- Postman (API Testing)

---

## 📂 Core Functionalities

- Create Leave Request
- View Leave Requests
- Update Leave Status
- Delete Leave Records
- Track Leave Status (Pending / Approved / Rejected)

---

## 🏗 Architecture

The application follows layered backend architecture:

Controller → Service → Repository → Database

Benefits:
- Clean separation of concerns
- Structured business logic
- Scalable backend design
- Maintainable codebase

---

## 🗄 Database Design

- Normalized relational schema
- JPA entity mapping
- Optimized CRUD operations
- Efficient repository queries

---

## 📬 API Testing

All REST endpoints were tested using Postman.

Example Endpoints:

- POST   /leaves
- GET    /leaves
- GET    /leaves/{id}
- PUT    /leaves/{id}
- DELETE /leaves/{id}

---

## ⚡ Performance Considerations

- Optimized repository-level queries
- Reduced redundant database calls
- Clean service-layer abstraction

---

## 🚀 Future Enhancements

- Add Spring Security authentication
- Implement Role-Based Access Control (RBAC)
- Integrate JWT authentication
- Add pagination & filtering
- Connect frontend UI

---

## 🎯 Key Learning Outcomes

- Designed RESTful API using Spring Boot
- Implemented CRUD operations using JPA
- Structured layered backend architecture
- Integrated relational database with ORM
- Tested API endpoints using Postman

---

## 👨‍💻 Author

**Ganta Lakshman Naga Durga Sairam**  
Java Full Stack Developer  
Hyderabad, India
