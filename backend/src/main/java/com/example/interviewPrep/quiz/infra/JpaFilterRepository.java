package com.example.interviewPrep.quiz.infra;

import com.example.interviewPrep.quiz.domain.Filter;
import com.example.interviewPrep.quiz.repository.FilterRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public interface JpaFilterRepository extends FilterRepository, JpaRepository<Filter, Long>{

    @Query("SELECT f.language FROM Filter f ORDER BY f.language ASC")
    List<String> findAllByLanguage();
}
