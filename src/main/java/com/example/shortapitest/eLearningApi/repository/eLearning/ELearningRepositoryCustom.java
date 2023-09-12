package com.example.shortapitest.eLearningApi.repository.eLearning;

import com.example.shortapitest.eLearningApi.dto.RequestELearning;
import com.example.shortapitest.eLearningApi.entity.eLearning.ELearning;

public interface ELearningRepositoryCustom {
    ELearning createELearning(RequestELearning requestELearning);
}
