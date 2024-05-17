package com.example.DailyReflectBackend.ExceptionHandlers;

import com.example.DailyReflectBackend.Exceptions.NoSuchUserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalUserExceptionHandler {
    @ExceptionHandler(value = NoSuchUserException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchEntryException(NoSuchUserException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
