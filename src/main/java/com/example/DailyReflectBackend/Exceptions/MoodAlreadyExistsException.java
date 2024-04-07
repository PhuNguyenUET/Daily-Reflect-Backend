package com.example.DailyReflectBackend.Exceptions;

import com.example.DailyReflectBackend.Model.Mood;

public class MoodAlreadyExistsException extends RuntimeException{
    private String message;

    public MoodAlreadyExistsException() {}

    public MoodAlreadyExistsException(String msg) {
        super(msg);
        this.message = msg;
    }
}
