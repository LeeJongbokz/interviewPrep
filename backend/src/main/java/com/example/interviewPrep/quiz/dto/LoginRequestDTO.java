package com.example.interviewPrep.quiz.dto;

import lombok.*;

@Builder
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {

    String email;
    String password;
}
