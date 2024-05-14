package com.example.DailyReflectBackend.Exceptions;

public class NoSuchMoodException extends RuntimeException{
    private String message;

    public NoSuchMoodException() {}

    public NoSuchMoodException(String msg) {
        super(msg);
        this.message = msg;
    }
}
