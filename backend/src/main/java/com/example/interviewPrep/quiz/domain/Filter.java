package com.example.interviewPrep.quiz.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Filter {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FILTER_ID")
    private Long id;

    @Column
    private String language;
}
