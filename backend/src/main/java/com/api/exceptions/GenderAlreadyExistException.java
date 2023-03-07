package com.api.exceptions;


public class GenderAlreadyExistException extends RuntimeException{
    private static final long serialVersionUID  = 1;

    public GenderAlreadyExistException(String message) {
        super(message);
    }
}
