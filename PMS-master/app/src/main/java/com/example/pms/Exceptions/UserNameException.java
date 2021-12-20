package com.example.pms.Exceptions;

public class UserNameException extends Exception {
    String message;
    public UserNameException() {
        super();
        message = "Enter a name greater than length 5";

    }
}
