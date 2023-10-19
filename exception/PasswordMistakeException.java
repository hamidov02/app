package com.eTaskifyAPI.etaskify.exception;

public class PasswordMistakeException extends RuntimeException{
    public PasswordMistakeException(String message) {
        super(message);
    }
}
