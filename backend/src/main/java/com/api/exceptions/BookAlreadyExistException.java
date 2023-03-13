package com.api.exceptions;

public class BookAlreadyExistException extends RuntimeException{
    private static final long serialVersionUID = 1;

    public BookAlreadyExistException(String message) {
        super(message);
    }
}
