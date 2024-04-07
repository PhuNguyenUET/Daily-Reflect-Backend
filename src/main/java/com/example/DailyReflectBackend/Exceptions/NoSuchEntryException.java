package com.example.DailyReflectBackend.Exceptions;

public class NoSuchEntryException extends RuntimeException{
    private String message;

    public NoSuchEntryException() {}

    public NoSuchEntryException(String msg) {
        super(msg);
        this.message = msg;
    }
}
