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

        int categorySequence = 1;
        int count = 0;

        // 1번 Dto에서 id 값을 추출하여 ELearningSetting에 해당하는 값이 있는지 조회
        ELearningSetting eLearningSetting = eLearningSettingRepository.findById(eLearningContentsDto.getELearningId())
                .orElseThrow(() -> new EntityNotFoundException("해당하는 ELearning이 없습니다."));

        // 2번 ELearningContent 생성
        ELearningContent eLearningContent = ELearningContent.createELearningContent(eLearningSetting);
        eLearningContentRepository.save(eLearningContent);
        //Setting에 Content 연결
        eLearningSetting.setELearningContent(eLearningContent);

        List<ELearningCategory> eLearningCategories = new ArrayList<>();
        List<ELearningMenu> eLearningMenuList = new ArrayList<>();

        eLearningContentsDto
                .getELearningCategoryDtos()
                .forEach(dto -> {
                            ELearningCategory category = ELearningCategory.createCategory(dto, eLearningContent);
                            dto.getMenu()
                                    .forEach(
                                            menuDto -> {
                                                ELearningMenu eLearningMenu = ELearningMenu.createManu(menuDto, category);
                                                int imageCount = menuDto.getMenuImageCount();
                                                List.of(menuImage.subList(0, imageCount)).forEach(image -> {
                                                    eLearningImageService.createMenuImage((MultipartFile)image,eLearningMenu);
                                                });
                                                ;
                                                menuImage.subList(0, imageCount).clear();
                                            }
                                    );
                            eLearningCategories.add(category);
                        }
                );

//                .stream()
//                .map(e -> ELearningCategory.createCategory(e,eLearningContent))
//                .toList();
//


        // 3번 ELearningCategory 생성 및 해당 하는 ELearningMenu 생성
        for (ELearningCategoryDto eLearningCategoryDto : eLearningContentsDto.getELearningCategoryDtos()) {
            int menuSequence = 1;
            //Content에 Category 연결
            ELearningCategory eLearningCategory = ELearningCategory.createCategory(eLearningCategoryDto, eLearningContent, categorySequence);
            eLearningCategoryRepository.save(eLearningCategory);
            eLearningContent.setELearningCategory(eLearningCategory);


            //메뉴
            for (ELearningMenuDto eLearningMenuDto : eLearningCategoryDto.getMenu()) {

                ELearningMenu eLearningMenu = ELearningMenu.createManu(eLearningMenuDto, menuSequence, eLearningCategory);
                eLearningMenuRepository.save(eLearningMenu);

                //Category와 menu 연걸
                eLearningCategory.setELearningMenu(eLearningMenu);

                for (int i = 0; i < eLearningMenuDto.getMenuImageCount(); i++) {
                    //조건식 수정 필요
                    //이미지 저장 로직
                    //이미지를 저장하고 이미지 n건을 eLeanringMenu에 넣어줍니다.
                    MenuImage createMenuImage = eLearningImageService.createMenuImage(menuImage.get(count), eLearningMenu);
                    count++;
                }

                menuSequence++;
            }
            categorySequence++;
        }
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
