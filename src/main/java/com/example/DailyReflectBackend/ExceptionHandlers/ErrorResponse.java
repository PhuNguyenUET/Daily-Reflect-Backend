package com.example.DailyReflectBackend.ExceptionHandlers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorResponse {
    private int statusCode;
    private String message;

    public ErrorResponse(String message) {
        super();
        this.message = message;
    }

    public ErrorResponse(int statusCode, String message) {
        super();
        this.message = message;
        this.statusCode = statusCode;
    }
}

