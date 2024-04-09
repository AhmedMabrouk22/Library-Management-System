# Library Management System API

## Overview

This project is a RESTful API for managing books, patrons, and borrowing records in a library. It's built using Spring Boot.

### Base URL

    http://localhost:8080/api

### End Point Documentation

    http://localhost:8080/swagger-ui/index.html

### Authentication

Authentication is not required for accessing the endpoints in this API.

### Error Handling
The API returns appropriate HTTP status codes and error messages in case of errors.

### Entities
The API deals with the following entities:

**Book**
- ID (auto-generated)
- Title
- Author
- ISBN
- Category
- Publication Year
- Page Numbers

**Patron**
- ID (auto-generated)
- Name
- Email
- Phone Number

**Borrowing Record**
- Tracks the association between books and patrons, including borrowing and return dates.

