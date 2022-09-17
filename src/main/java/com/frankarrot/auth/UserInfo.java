package com.frankarrot.auth;

import com.frankarrot.member.domain.Member;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserInfo {
    private String id;
    private String email;
    private String name;
    private String imageUrl;

    public UserInfo(String id, String email, String name, String imageUrl) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public static UserInfo from(Map<String, Object> oauthAttributes) {
        return UserInfo.builder()
                .id((String) oauthAttributes.get("id"))
                .email((String) oauthAttributes.get("email"))
                .name((String) oauthAttributes.get("name"))
                .imageUrl((String) oauthAttributes.get("avatar_url"))
                .build();
    }

    public Member toMember() {
        return Member.builder()
                .oauthId(id)
                .name(name)
                .email(email)
                .imageUrl(imageUrl)
                .build();
    }
}
