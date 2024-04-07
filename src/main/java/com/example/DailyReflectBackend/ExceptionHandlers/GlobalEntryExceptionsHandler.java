package com.example.DailyReflectBackend.ExceptionHandlers;

import com.example.DailyReflectBackend.Exceptions.EntryAlreadyExistsException;
import com.example.DailyReflectBackend.Exceptions.NoSuchEntryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalEntryExceptionsHandler {
    @ExceptionHandler(value = NoSuchEntryException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchEntryException(NoSuchEntryException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(value = EntryAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEntryAlreadyExistsException(EntryAlreadyExistsException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
}
