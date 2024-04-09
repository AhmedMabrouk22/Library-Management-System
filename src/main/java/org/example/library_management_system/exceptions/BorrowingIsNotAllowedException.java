package org.example.library_management_system.exceptions;

public class BorrowingIsNotAllowedException extends RuntimeException{
    public BorrowingIsNotAllowedException(String message) {
        super(message);
    }
}
