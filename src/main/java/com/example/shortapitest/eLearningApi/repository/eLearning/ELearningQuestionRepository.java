package com.example.shortapitest.eLearningApi.repository.eLearning;

import com.example.shortapitest.eLearningApi.entity.eLearning.question.ELearningQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ELearningQuestionRepository extends JpaRepository<ELearningQuestion, Long> {
}
