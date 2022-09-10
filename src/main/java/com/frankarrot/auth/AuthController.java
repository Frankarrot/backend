package com.frankarrot.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login/{provider}")
    public ResponseEntity<LoginResponse> login(@PathVariable String provider, @RequestBody TokenRequest tokenRequest) {
        LoginResponse loginResponse = authService.login(provider, tokenRequest.getCode());
        return ResponseEntity.ok(loginResponse);
    }

    // 여기에 같이 적어보면 어떨까 헤헤 ㅋㅋ

    // [프론트]
    // 1. 프론트에서 OAuth 버튼을 누른다

    // [Github]
    // 2. Github 로그인 페이지로 이동되어 로그인한다
    // 3. 로그인 성공 시 프론트로 Redirect 된다. 이떄 code=adsf 가 URL에 같이 전달된다

    // [프론트]
    // 4. 프론트가 code를 백엔드에게 전송한다

    // [백엔드]
    // 5. 백엔드는 code를 Github에 전송해서 AccessToken으로 교환한다.
    // 6. 백엔드가 다시 AccessToken 으로 Github에 요청해서 사용자 정보를 받아온다
    // 7. 신규 회원이면 저장, 기존 회원이면 저장 안함
    // 8. 백엔드가 사용자 정보를 이용해서 AccessToken을 만들어서 반환한다ㅋ

    // [프론트]
    // 9. 프론트가 AccessToken을 LocalStorage 또는 Cookie에 저장한다
    // 10. 프론트가 인증/인가가 필요한 페이지로 이동하며 AccessToken을 같이 전송한다

}
