package com.example.text_task.exception;

public class TextException extends Exception {
    public TextException(String message) {
        super(message);
    }

    public TextException(String message, Throwable cause) {
        super(message, cause);
    }
}
