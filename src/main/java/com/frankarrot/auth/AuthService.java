package com.frankarrot.auth;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AuthService {

    private final GithubOauthConfig githubOauthConfig;

    public AuthService(GithubOauthConfig githubOauthConfig) {
        this.githubOauthConfig = githubOauthConfig;
    }

    public LoginResponse login(String provider, String code) {
        GithubAccessTokenResponse block = WebClient.create()
                .post()
                .uri(githubOauthConfig.getTokenUri())
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(tokenRequest(code)), Map.class)
                .retrieve()
                .bodyToMono(GithubAccessTokenResponse.class)
                .block();

        return null;
    }

    private Map<String, String> tokenRequest(String code) {
        Map<String, String> formData = new HashMap<>();
        formData.put("code", code);
        formData.put("client_id", githubOauthConfig.getClientId());
        formData.put("client_secret", githubOauthConfig.getClientSecret());
        return formData;
    }
}
