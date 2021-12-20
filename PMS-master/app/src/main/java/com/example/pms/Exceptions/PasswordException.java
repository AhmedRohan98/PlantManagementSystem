package com.example.pms.Exceptions;

public class PasswordException extends Exception{
    String message;
    public PasswordException() {
        super();
        message = "Password Length Must be Greater Than or Equals to 8";

    }


}
