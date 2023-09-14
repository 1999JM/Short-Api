package com.example.shortapitest.eLearningApi.service;

import com.example.shortapitest.eLearningApi.dto.*;
import com.example.shortapitest.eLearningApi.entity.eLearning.ELearningSetting;
import com.example.shortapitest.eLearningApi.entity.eLearning.content.ELearningCategory;
import com.example.shortapitest.eLearningApi.entity.eLearning.content.ELearningContent;
import com.example.shortapitest.eLearningApi.entity.eLearning.content.ELearningMenu;
import com.example.shortapitest.eLearningApi.entity.eLearning.question.ELearningQuestion;
import com.example.shortapitest.eLearningApi.entity.image.CoverImage;
import com.example.shortapitest.eLearningApi.entity.image.LogoImage;
import com.example.shortapitest.eLearningApi.entity.image.MenuImage;
import com.example.shortapitest.eLearningApi.repository.eLearning.ELearningCategoryRepository;
import com.example.shortapitest.eLearningApi.repository.eLearning.ELearningContentRepository;
import com.example.shortapitest.eLearningApi.repository.eLearning.ELearningMenuRepository;
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

    private final ELearningMenuRepository eLearningMenuRepository;

    @Transactional
    public void eLearningSettingCreate(ELearningSettingDto ELearningSettingDto, MultipartFile logoImage, MultipartFile coverImage) {
        LogoImage createLogoImage = eLearningImageService.createLogoImage(logoImage);
        CoverImage createCoverImage = eLearningImageService.createCoverImage(coverImage);
        ELearningSetting eLearningSetting = ELearningSetting.createELearningSetting(ELearningSettingDto, createLogoImage, createCoverImage);
        eLearningSettingRepository.save(eLearningSetting);
    }

    @Transactional
    public void eLearningContentsCreate(ELearningContentsDto eLearningContentsDto, List<MultipartFile> menuImage) {

        // 1번 Dto에서 id 값을 추출하여 ELearningSetting에 해당하는 값이 있는지 조회
        ELearningSetting eLearningSetting = eLearningSettingRepository.findById(eLearningContentsDto.getELearningId())
                .orElseThrow(() -> new EntityNotFoundException("해당하는 ELearning이 없습니다."));

        // 2번 ELearningContent 생성
        ELearningContent eLearningContent = ELearningContent.createELearningContent(eLearningSetting);
        eLearningContentRepository.save(eLearningContent);
        //Setting에 Content 연결
        eLearningSetting.setELearningContent(eLearningContent);

        eLearningContentsDto
                .getELearningCategoryDtos()
                .forEach(dto -> {
                            ELearningCategory category = ELearningCategory.createCategory(dto, eLearningContent);
                            eLearningCategoryRepository.save(category);
                            dto.getMenu()
                                    .forEach(
                                            menuDto -> {
                                                ELearningMenu eLearningMenu = ELearningMenu.createManu(menuDto, category);
                                                eLearningMenuRepository.save(eLearningMenu);
                                                menuImage.subList(0, menuDto.getMenuImageCount())
                                                        .forEach(
                                                                image -> {
                                                    eLearningImageService.createMenuImage(image,eLearningMenu);
                                                }
                                                );
                                                ;
                                                menuImage.subList(0, menuDto.getMenuImageCount()).clear();
                                            }
                                    );
                        }
                );
//        }
    }

    @Transactional
    public void eLearningQuestionCreate(ELearningQuestionDto eLearningQuestionDto, List<MultipartFile> questionImages) {

        // 1번 문제 등록
        System.out.println(eLearningQuestionDto.toString());

        for (ELearningQuestionSetDto eLearningQuestionSetDto : eLearningQuestionDto.getELearningQuestionSetDtos()) {

            //  ELearningQuestion
        }


        // 2번 이미지 등록

        // 선택 항목을 등록


    }

}
