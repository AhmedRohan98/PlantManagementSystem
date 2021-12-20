package com.example.pms.Exceptions;

public class ValueException extends Exception{
    String message;
    public ValueException() {
        super();
        message = "Please enter a value greater than 10";

    }
}
