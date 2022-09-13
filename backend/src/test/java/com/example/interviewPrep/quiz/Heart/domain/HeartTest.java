package com.example.interviewPrep.quiz.Heart.domain;

import com.example.interviewPrep.quiz.domain.Heart;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HeartTest {

    @Test
    void creationWithId() {
        Heart heart = Heart.builder()
                .id(1L)
                .build();
        assertThat(heart.getId()).isEqualTo(1L);
    }
}
