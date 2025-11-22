# Project Title:
# Secure-Email-System

# Project Overview

The Secure Email System is an advanced, web-based email application designed to provide secure email communication with robust user authentication, role-based access control, and encrypted email storage. The system is implemented using a full-stack architecture, combining Spring Boot for the backend, MySQL for data persistence, and Angular for the frontend.

The system aims to address key security challenges in email communication, including unauthorized access, data leakage, and secure storage of sensitive information.

# Technologies Used

# Backend:

Java, Spring Boot
Spring Data JPA (for database access)
Spring Security (for authentication and authorization)
JavaMail API (Docker Email Testing)
JWT (JSON Web Token) for stateless authentication
AES-256 encryption for email content
MySQL (Relational Database)

# Frontend:

Angular (SPA framework)
TypeScript, HTML, SCSS
Angular Guards for route protection

# DevOps & Deployment:

Docker for containerization
Application properties (application.yml) for configuration management

# Libraries / Tools:

Lombok (for reducing boilerplate code)
jwt-decode (Angular library to decode JWT tokens)
Axios (for HTTP requests from frontend)

# Architecture Overview

# Frontend (Angular):

Provides a responsive UI for login, dashboard, inbox, sent emails, and admin management.
Handles JWT token storage and role-based routing.

# Backend (Spring Boot):

Handles user authentication, email composition, sending, receiving, and history storage.
Implements role-based access control (USER and ADMIN).
Uses AES encryption for secure storage of email content in the database.
Generates JWT tokens including user role and metadata.

# Database (MySQL):

Stores users, emails, attachments, login history, and system logs.
Encrypted email content ensures sensitive data is never stored in plaintext.

# Security Layer:

JWT authentication for stateless session management.
Spring Security filters to protect APIs based on user roles.
Angular Guards prevent unauthorized access to protected routes.

# Key Features and Functionalities
# User Module

Registration and Login: Secure authentication using hashed passwords (BCrypt) and JWT tokens.
Inbox & Sent Emails: Users can compose, send, and view emails.
Email Encryption: All email contents are encrypted using AES-256 before storage.
Role-based Access: Only authorized users can access certain features.

# Admin Module

User Management: Admins can view all users, disable or enable accounts.
Audit Logs: Admins can access system logs and login history to monitor activities.
Secure Access: Only admin users can access the admin dashboard, enforced via JWT roles and Angular guards.

# Security Features

JWT Tokens: Include user role, email, UUID, and full name.
AES Encryption: Secures sensitive email content in the database.
Spring Security + Angular Guards: Protect backend endpoints and frontend routes.
Email Protocols: SMTP for sending emails; POP3/IMAP for retrieving emails.

# Optional / Advanced Features

Attachment management with secure storage.
Login history tracking for security audits.
Dockerized backend for easy deployment and scalability.

# Project Workflow

# User Registration & Login:

Password is hashed and stored.
JWT token is generated with embedded role and metadata.

# Sending an Email:

Email content is encrypted via AES-256.
Email is sent via SMTP and stored in the database.

# Receiving Emails:

Emails are retrieved via POP3/IMAP.
Decrypted on-demand for display in the inbox.

# Role-based Redirection:

Angular decodes JWT to determine user role.
Users are redirected to inbox, Admins to admin dashboard.

# Admin Actions:

Admin can view and manage users and system logs.
Unauthorized access is prevented via backend Spring Security and Angular Guards.

# Outcome / Achievements

Developed a secure, role-based email system with encryption.
Implemented modern web security practices including JWT, AES, and hashed passwords.
Provided a scalable and modular architecture suitable for microservices expansion.
Enabled admin-level monitoring for enhanced system control.
Learned integration of email protocols (SMTP/POP3/IMAP) with Spring Boot.

# Conclusion

This project demonstrates the development of a full-stack secure email system combining security, usability, and modular architecture. It serves as an excellent example of integrating Java backend technologies, secure protocols, encryption mechanisms, and a modern Angular frontend to solve real-world secure communication challenges.
