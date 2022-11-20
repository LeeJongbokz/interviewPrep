package com.example.interviewPrep.quiz.member;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class MemberContext extends User {

    private final Member member;

    public MemberContext(Member member, Collection<? extends GrantedAuthority> authorities){
        super(member.getEmail(), member.getPassword(), authorities);

        this.member = member;
    }

    public Member getMember(){
        return member;
    }
}
