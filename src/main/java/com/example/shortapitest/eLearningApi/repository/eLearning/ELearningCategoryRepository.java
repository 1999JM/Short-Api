package com.example.shortapitest.eLearningApi.repository.eLearning;

import com.example.shortapitest.eLearningApi.entity.eLearning.ELearningCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ELearningCategoryRepository extends JpaRepository<ELearningCategory, Long>, ELearningCategoryRepositoryCustom {
}
