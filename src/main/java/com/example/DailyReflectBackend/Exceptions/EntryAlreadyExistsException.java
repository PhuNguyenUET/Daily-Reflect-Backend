package com.example.DailyReflectBackend.Exceptions;

public class EntryAlreadyExistsException extends RuntimeException{
    private String message;

    public EntryAlreadyExistsException() {}

    public EntryAlreadyExistsException(String msg) {
        super(msg);
        this.message = msg;
    }
}
