package com.frankarrot.auth.exception;

public class UnauthenticatedException extends RuntimeException{

    public UnauthenticatedException(String message) {
        super(message);
    }
}
