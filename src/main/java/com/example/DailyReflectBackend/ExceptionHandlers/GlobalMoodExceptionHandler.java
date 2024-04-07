package com.example.DailyReflectBackend.ExceptionHandlers;

import com.example.DailyReflectBackend.Exceptions.MoodAlreadyExistsException;
import com.example.DailyReflectBackend.Exceptions.NoSuchMoodException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalMoodExceptionHandler {
    @ExceptionHandler(value = NoSuchMoodException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchMoodException(NoSuchMoodException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(value = MoodAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleMoodAlreadyExistsException(MoodAlreadyExistsException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
}
