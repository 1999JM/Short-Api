package com.example.shortapitest.eLearningApi.repository.eLearning;

import com.example.shortapitest.eLearningApi.entity.eLearning.question.ELearningChoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ELearningChoiceRepository extends JpaRepository<ELearningChoice, Long> {
}
