package com.example.DailyReflectBackend.Exceptions;

public class NoSuchUserException extends RuntimeException{
    private String message;

    public NoSuchUserException() {}

    public NoSuchUserException(String msg) {
        super(msg);
        this.message = msg;
    }
}