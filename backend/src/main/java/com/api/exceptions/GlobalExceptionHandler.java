package com.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GenderNotFoundException.class)
    public ResponseEntity<ErrorObject> handleGenderNotFoundException(GenderNotFoundException ex, WebRequest webRequest){
        ErrorObject errorObject = new ErrorObject();
        setErrorObject(errorObject, HttpStatus.NOT_FOUND, ex);
        return new ResponseEntity<>(errorObject, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(GenderAlreadyExistException.class)
    public ResponseEntity<ErrorObject> handleGenderAlreadyExistException(GenderAlreadyExistException ex, WebRequest webRequest){
        ErrorObject errorObject = new ErrorObject();
        setErrorObject(errorObject, HttpStatus.NOT_ACCEPTABLE, ex);
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorObject);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorObject> handleBookNotFoundException(BookNotFoundException ex, WebRequest webRequest){
        ErrorObject errorObject = new ErrorObject();
        setErrorObject(errorObject, HttpStatus.NOT_FOUND, ex);
        return new ResponseEntity<>(errorObject, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(BookAlreadyExistException.class)
    public ResponseEntity<ErrorObject> handleBookAlreadyExistException(BookAlreadyExistException ex, WebRequest webRequest){
        ErrorObject errorObject = new ErrorObject();
        setErrorObject(errorObject, HttpStatus.NOT_ACCEPTABLE, ex);
        return new ResponseEntity<>(errorObject, HttpStatus.NOT_ACCEPTABLE);
    }

    private void setErrorObject(ErrorObject errorObject, HttpStatus errorValue, Exception ex){
        errorObject.setStatusCode(errorValue.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimestamp(new Date());
    }
}
