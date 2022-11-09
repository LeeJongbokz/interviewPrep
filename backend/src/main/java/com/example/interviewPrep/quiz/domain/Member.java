package com.example.interviewPrep.quiz.domain;

import lombok.*;

import javax.persistence.*;

@Setter
@Entity
@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(indexes = @Index(name= "i_member", columnList = "email"))
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MEMBER_ID")
    private Long id;

    @Column(nullable = false)
    private String email;

    private String password;

    private String type;

    private String name;

    private String refreshToken;
    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Member update(String name, String picture){
        this.name = name;
        this.picture = picture;

        return this;
    }

    public Member updateToken(String refreshToken){
        this.refreshToken = refreshToken;
        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }

}
