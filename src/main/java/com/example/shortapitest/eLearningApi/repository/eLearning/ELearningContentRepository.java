package com.example.shortapitest.eLearningApi.repository.eLearning;

import com.example.shortapitest.eLearningApi.entity.eLearning.content.ELearningContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ELearningContentRepository extends JpaRepository<ELearningContent, Long> {
}
