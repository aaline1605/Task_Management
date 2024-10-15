package com.task_management.Task_Management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleResponseStatusException(ResponseStatusException ex) {
        // Use the ex.getReason() to retrieve the error message
        String errorMessage = ex.getReason();

        // Convert HttpStatusCode to HttpStatus
        HttpStatus status = HttpStatus.valueOf(ex.getStatusCode().value()); // Convert to HttpStatus

        return new ResponseEntity<>(errorMessage, status);
    }
}


