package com.example.shortapitest.eLearningApi.service;

import com.example.shortapitest.eLearningApi.dto.RequestELearning;
import com.example.shortapitest.eLearningApi.dto.RequestELearningCategory;
import com.example.shortapitest.eLearningApi.dto.RequestELearningQuestion;
import com.example.shortapitest.eLearningApi.entity.eLearning.ELearning;
import com.example.shortapitest.eLearningApi.entity.eLearning.ELearningCategory;
import com.example.shortapitest.eLearningApi.entity.image.LogoImage;
import com.example.shortapitest.eLearningApi.repository.eLearning.*;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ELearningService {

    private final ELearningRepository eLearningRepository;

    private final ELearningCategoryRepository eLearningCategoryRepository;

    private final ELearningMenuRepository eLearningMenuRepository;

    private final ELearningQuestionRepository eLearningQuestionRepository;

    private final ELearningChoiceRepository eLearningChoiceRepository;

    @Transactional
    public void eLearningCreate(RequestELearning requestELearning
            ,List<RequestELearningCategory> requestELearningCategories
            ,List<RequestELearningQuestion> requestELearningQuestion
            ,MultipartFile logoImage){

        //LogoImage saveLogoImage =

        ELearning eLearning = eLearningRepository.createELearning(requestELearning);
        List<ELearningCategory> eLearningCategories = eLearningCategoryRepository.createELearningCategory(eLearning, requestELearningCategories);
    }
}
