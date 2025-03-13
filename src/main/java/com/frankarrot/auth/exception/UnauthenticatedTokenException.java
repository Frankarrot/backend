package com.frankarrot.auth.exception;

public class UnauthenticatedTokenException extends UnauthenticatedException {

    public UnauthenticatedTokenException() {
        super("토큰이 유효하지 않습니다.");
    }
}
