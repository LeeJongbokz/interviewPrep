package com.example.interviewPrep.quiz.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Heart {
    @Id
    @GeneratedValue
    @Column(name = "HEART_ID")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "ANSWER_ID")
    Answer answer;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "QUESTION_ID")
    Question question;
}
