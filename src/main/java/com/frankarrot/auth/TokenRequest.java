package com.frankarrot.auth;

import lombok.Getter;

@Getter
public class TokenRequest {

    private String code;

    private TokenRequest() {
    }

    public TokenRequest(String code) {
        this.code = code;
    }
}
