package com.example.interviewPrep.quiz.domain;

import java.util.List;

public interface HeartRepository {
    List<Heart> findByAnswerId(Long id);
    int countHeartByAnswerId(long id);
}
