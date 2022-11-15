package com.example.interviewPrep.quiz.service;

import com.example.interviewPrep.quiz.domain.Member;
import com.example.interviewPrep.quiz.domain.MemberContext;
import com.example.interviewPrep.quiz.exception.MemberNotFoundException;
import com.example.interviewPrep.quiz.infra.JpaMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService{

    private final JpaMemberRepository jpaMemberRepository;

    @Override
    public UserDetails loadUserByUsername(String memberId) throws MemberNotFoundException{

        Member member = jpaMemberRepository.findById(Long.parseLong(memberId)).orElseThrow(() -> new MemberNotFoundException("해당 멤버가 존재하지 않습니다."));

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ROLE_USER"));

        MemberContext memberContext = new MemberContext(member, roles);

        return memberContext;

    }
}