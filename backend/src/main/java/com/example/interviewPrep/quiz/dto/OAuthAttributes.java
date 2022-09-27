package com.example.interviewPrep.quiz.dto;

import com.example.interviewPrep.quiz.domain.Member;
import com.example.interviewPrep.quiz.domain.Role;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes){
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes){
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public Member toEntity(){ // 유저 eneity 생성
        return Member.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.USER) // 가입 시 기본 권한은 user(일반 사용자)
                .build();
    }





}
