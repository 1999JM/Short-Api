package com.example.shortapitest.eLearningApi.service;

import com.example.shortapitest.eLearningApi.dto.ELearningCategoryDto;
import com.example.shortapitest.eLearningApi.dto.ELearningContentsDto;
import com.example.shortapitest.eLearningApi.dto.ELearningMenuDto;
import com.example.shortapitest.eLearningApi.dto.ELearningSettingDto;
import com.example.shortapitest.eLearningApi.entity.eLearning.ELearningSetting;
import com.example.shortapitest.eLearningApi.entity.eLearning.content.ELearningCategory;
import com.example.shortapitest.eLearningApi.entity.eLearning.content.ELearningContent;
import com.example.shortapitest.eLearningApi.entity.eLearning.content.ELearningMenu;
import com.example.shortapitest.eLearningApi.entity.image.CoverImage;
import com.example.shortapitest.eLearningApi.entity.image.LogoImage;
import com.example.shortapitest.eLearningApi.repository.eLearning.ELearningCategoryRepository;
import com.example.shortapitest.eLearningApi.repository.eLearning.ELearningContentRepository;
import com.example.shortapitest.eLearningApi.repository.eLearning.ELearningSettingRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ELearningService {

    private final ELearningImageService eLearningImageService;

    private final ELearningSettingRepository eLearningSettingRepository;

    private final ELearningContentRepository eLearningContentRepository;

    private final ELearningCategoryRepository eLearningCategoryRepository;


    @Transactional
    public void eLearningSettingCreate(ELearningSettingDto ELearningSettingDto, MultipartFile logoImage, MultipartFile coverImage){
        LogoImage createLogoImage = eLearningImageService.createLogoImage(logoImage);
        CoverImage createCoverImage = eLearningImageService.createCoverImage(coverImage);
        ELearningSetting eLearningSetting = ELearningSettingDto.createELearningSetting(ELearningSettingDto, createLogoImage, createCoverImage);
        eLearningSettingRepository.save(eLearningSetting);
    }

    @Transactional
    public void eLearningContentsCreate(ELearningContentsDto eLearningContentsDto, List<MultipartFile> menuImage) {

        // 1번 Dto에서 id 값을 추출하여 ELearningSetting에 해당하는 값이 있는지 조회
        ELearningSetting eLearningSetting = eLearningSettingRepository.findById(eLearningContentsDto.getELearningId())
                .orElseThrow(() -> new EntityNotFoundException("해당하는 ELearning이 없습니다."));

        // 2번 ELearningContent 생성
        ELearningContent eLearningContent = ELearningContent.builder()
                .eLearningSetting(eLearningSetting)
                .build();

        ELearningContent saveELearningContent = eLearningContentRepository.save(eLearningContent);

        // 3번 ELearningCategory 생성 및 해당하는 ELearningMenu 생성
        List<ELearningCategory> eLearningCategories = new ArrayList<>();

        for (ELearningCategoryDto eLearningCategoryDto : eLearningContentsDto.getELearningCategoryDtos()) {

            ELearningCategory eLearningCategory = ELearningCategory.createCategory(eLearningCategoryDto,eLearningContent);
            eLearningCategories.add(eLearningCategoryRepository.save(eLearningCategory));

            for (ELearningMenuDto eLearningMenuDto : eLearningCategoryDto.getMenu()) {


            }
        }

        //Setting에 Content 연결
        eLearningSetting.setELearningContent(saveELearningContent);

        //Content에 Category 연결
        saveELearningContent.setELearningCategory(eLearningCategories);
    }
}
