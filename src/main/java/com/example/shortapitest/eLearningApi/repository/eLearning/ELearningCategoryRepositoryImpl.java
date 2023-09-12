package com.example.shortapitest.eLearningApi.repository.eLearning;

import com.example.shortapitest.eLearningApi.dto.RequestELearningCategory;
import com.example.shortapitest.eLearningApi.entity.eLearning.ELearning;
import com.example.shortapitest.eLearningApi.entity.eLearning.ELearningCategory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ELearningCategoryRepositoryImpl implements ELearningCategoryRepositoryCustom{

    private final EntityManager entityManager;
    @Override
    public List<ELearningCategory> createELearningCategory(ELearning eLearning, List<RequestELearningCategory> requestELearningCategories) {

        List<ELearningCategory> eLearningCategories = new ArrayList<>();

        for (RequestELearningCategory requestELearningCategory : requestELearningCategories){
            ELearningCategory eLearningCategory= ELearningCategory.builder()
                    .categoryName(requestELearningCategory.getCategoryName())
                    .eLearning(eLearning)
                    .build();

            eLearningCategories.add(eLearningCategory);

            entityManager.persist(eLearningCategory);
            eLearning.setELearningCategory(eLearningCategory);
        }
        return eLearningCategories;
    }
}
