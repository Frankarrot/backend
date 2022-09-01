package com.frankarrot.auth;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@ConfigurationProperties(prefix = "oauth.github")
public class GithubOauthConfig {

    private final String clientId;
    private final String clientSecret;
    private final String tokenUri;
    private final String userInfoUri;

    public GithubOauthConfig(String clientId, String clientSecret, String tokenUri, String userInfoUri) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.tokenUri = tokenUri;
        this.userInfoUri = userInfoUri;
    }
}
