package com.api.exceptions;

public class GenderNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1;

    public GenderNotFoundException(String message) {
        super(message);
    }
}
