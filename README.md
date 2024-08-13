# Library Management System API

## Overview

This documentation provides details on the endpoints and functionalities of the Library Management System API built using Spring Boot. The API allows librarians to manage books, patrons, and borrowing records.

### Base URL

    http://localhost:8080/api

### API Documentation

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

