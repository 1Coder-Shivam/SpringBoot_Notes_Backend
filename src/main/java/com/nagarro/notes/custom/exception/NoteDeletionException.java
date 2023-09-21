package com.nagarro.notes.custom.exception;

public class NoteDeletionException extends RuntimeException {
    public NoteDeletionException(String message) {
        super(message);
    }
}