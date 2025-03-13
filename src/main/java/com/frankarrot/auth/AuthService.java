package com.frankarrot.auth;

import com.frankarrot.auth.dto.GithubAccessTokenResponse;
import com.frankarrot.auth.dto.LoginResponse;
import com.frankarrot.auth.exception.OauthUserInfoRequestFailedException;
import com.frankarrot.auth.support.JwtTokenProvider;
import com.frankarrot.member.domain.Member;
import com.frankarrot.member.domain.MemberRepository;
import java.util.HashMap;
import java.util.Map;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AuthService {

    private final MemberRepository memberRepository;
    private final GithubOauthConfig githubOauthConfig;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthService(MemberRepository memberRepository, GithubOauthConfig githubOauthConfig,
                       JwtTokenProvider jwtTokenProvider) {
        this.memberRepository = memberRepository;
        this.githubOauthConfig = githubOauthConfig;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public LoginResponse login(String provider, String code) {
        //accessToken 가져오기
        GithubAccessTokenResponse token = getToken(code);

        //UserInfo 가져오기
        UserInfo userInfo = getUserInfo(token.getAccessToken());

        //DB에 저장
        Member member = saveOrUpdate(userInfo);

        //로그인 용도Token 반환
        return new LoginResponse(jwtTokenProvider.createToken(String.valueOf(member.getId())));
    }

    private GithubAccessTokenResponse getToken(String code) {
        return WebClient.create()
                .post()
                .uri(githubOauthConfig.getTokenUri())
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(tokenRequest(code)), Map.class)
                .retrieve()
                .bodyToMono(GithubAccessTokenResponse.class)
                .block();
    }

    private Map<String, String> tokenRequest(String code) {
        Map<String, String> formData = new HashMap<>();
        formData.put("code", code);
        formData.put("client_id", githubOauthConfig.getClientId());
        formData.put("client_secret", githubOauthConfig.getClientSecret());
        return formData;
    }

    private UserInfo getUserInfo(String accessToken) {
        Map<String, Object> githubAuthAttributes = WebClient.create()
                .get()
                .uri(githubOauthConfig.getUserInfoUri())
                .headers(header -> header.setBearerAuth(accessToken))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
                })
                .blockOptional()
                .orElseThrow(OauthUserInfoRequestFailedException::new);
        return UserInfo.from(githubAuthAttributes);
    }

    private Member saveOrUpdate(UserInfo userInfo) {
        Member member = memberRepository.findByOauthId(userInfo.getId())
                .map(it -> it.update(userInfo.getName(), userInfo.getEmail(), userInfo.getImageUrl()))
                .orElseGet(userInfo::toMember);
        return memberRepository.save(member);
    }
}
