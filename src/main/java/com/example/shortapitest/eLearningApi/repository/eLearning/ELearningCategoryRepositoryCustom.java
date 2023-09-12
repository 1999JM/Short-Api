package com.example.shortapitest.eLearningApi.repository.eLearning;

import com.example.shortapitest.eLearningApi.dto.RequestELearningCategory;
import com.example.shortapitest.eLearningApi.entity.eLearning.ELearning;
import com.example.shortapitest.eLearningApi.entity.eLearning.ELearningCategory;
import jakarta.persistence.EntityManager;

import java.util.List;

public interface ELearningCategoryRepositoryCustom {

    List<ELearningCategory> createELearningCategory(ELearning eLearning
            ,List<RequestELearningCategory> requestELearningCategories );
}
