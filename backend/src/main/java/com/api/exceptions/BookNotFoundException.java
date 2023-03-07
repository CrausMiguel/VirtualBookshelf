package com.api.exceptions;

public class BookNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1;

    public BookNotFoundException(String message) {
        super(message);
    }
}
