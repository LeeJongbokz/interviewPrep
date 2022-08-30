package com.example.interviewPrep.quiz.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(indexes = @Index(name= "i_member", columnList = "email"))
public class Member {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name="MEMBER_ID")
    private Long id;

    private String email;
    private String password;
    private String type;
}
