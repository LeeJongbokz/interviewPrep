package com.example.interviewPrep.quiz.Question.repository;

import com.example.interviewPrep.quiz.domain.Question;
import com.example.interviewPrep.quiz.repository.QuestionRepository;
import com.example.interviewPrep.quiz.service.QuestionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class QuestionRepositoryTest {

    @Autowired
    QuestionRepository questionRepository;

    @Test
    @DisplayName("Question을 DB에 저장")
    public void save(){

        // Given
        Question question = Question.builder()
                .title("problem313")
                .type("java")
                .build();

        // When
        questionRepository.save(question);

        // Then
        Long savedId = question.getId();
        assertEquals(question, questionRepository.findById(savedId));

    }

    @Test
    @DisplayName("Question을 DB에 업데이트")
    public void update(){

        // Given
        Question question = questionRepository.findById(5L);

        // When
        question.change("새 문제입니다3334", "java");

        // Then
        questionRepository.update(question);
        Question updatedQuestion = questionRepository.findById(5L);

        assertThat(question.getTitle()).isEqualTo(updatedQuestion.getTitle());

    }

    @Test
    @DisplayName("Question을 DB에서 삭제")
    public void delete(){

        // Given
        Question question = questionRepository.findById(5L);

        // When
        questionRepository.delete(question);

        // Then
        assertThat(questionRepository.findById(5L)).isEqualTo(null);

    }

    /*
    @Test
    @DisplayName("Question을 id로 검색")
    public void findById() throws Exception{

        // Given
        Question question = Question.builder()
                .title("problem345")
                .type("java")
                .build();

        // When
        questionRepository.save(question);

        // Then
        Question savedQuestion = questionRepository.findByTitle("problem345").get(0);
        assertThat(savedQuestion.getId()).isEqualTo(333L);
    }
    */


    @Test
    @DisplayName("Question을 type으로 검색")
    public void findByTitle(){

        // Given
        Question question = Question.builder()
                .title("problem356")
                .type("java")
                .build();

        // When
        questionRepository.save(question);

        // Then
        Question result = questionRepository.findByTitle("problem356").get(0);
        assertThat(question).isEqualTo(result);

    }

    @Test
    @DisplayName("Question을 type으로 검색")
    public void findByType(){

        // Given
        Question question = Question.builder()
                .title("problem333")
                .type("java22")
                .build();

        // When
        questionRepository.save(question);

        // Then
        Question result = questionRepository.findByType("java22").get(0);
        assertThat(question).isEqualTo(result);

    }

}