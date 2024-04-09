package org.example.library_management_system.exceptions;


import jakarta.validation.ConstraintViolationException;
import org.example.library_management_system.handler.ErrorResponseHandler;
import org.example.library_management_system.utils.HttpStatusMessage;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseHandler> handelNotFoundException(NotFoundException ex) {
        return ErrorResponseHandler.generateResponse(HttpStatusMessage.FAIL,HttpStatus.NOT_FOUND,ex.getMessage());
    }

    @ExceptionHandler(BorrowingIsNotAllowedException.class)
    public ResponseEntity<ErrorResponseHandler> handleBookIsBorrowedException(BorrowingIsNotAllowedException ex) {
        return ErrorResponseHandler.generateResponse(HttpStatusMessage.FAIL,HttpStatus.BAD_REQUEST,ex.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponseHandler> handleDuplicateKeyViolation(DataIntegrityViolationException ex) {
        String key = extractKey(ex.getMessage());
        String value = extractValue(ex.getMessage());
        String message = "duplicate key: " + key + " With value: " + value;
        return ErrorResponseHandler.generateResponse(HttpStatusMessage.FAIL,HttpStatus.BAD_REQUEST,message);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseHandler> handleValidationException(ConstraintViolationException ex) {
        StringBuilder message = new StringBuilder();
        ex.getConstraintViolations().forEach(val -> message.append(val.getPropertyPath()).append(": ").append(val.getMessage()).append("; "));
        return ErrorResponseHandler.generateResponse(HttpStatusMessage.FAIL,HttpStatus.BAD_REQUEST,message.toString());
    }


    private static String extractKey(String errorMessage) {
        Pattern pattern = Pattern.compile("Key \\((.*?)\\)=\\((.*?)\\)");
        Matcher matcher = pattern.matcher(errorMessage);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "Unknown key";
    }

    private static String extractValue(String errorMessage) {
        Pattern pattern = Pattern.compile("Key \\((.*?)\\)=\\((.*?)\\)");
        Matcher matcher = pattern.matcher(errorMessage);
        if (matcher.find()) {
            return matcher.group(2);
        }
        return "Unknown value";
    }
}
