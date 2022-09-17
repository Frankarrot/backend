package com.frankarrot.auth.exception;

public class OauthUserInfoRequestFailedException extends InvalidRequestException {

    public OauthUserInfoRequestFailedException() {
        super("유효하지 않은 토큰입니다.");
    }
}
