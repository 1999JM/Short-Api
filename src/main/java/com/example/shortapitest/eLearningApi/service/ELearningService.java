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
    public void eLearningSettingCreate(ELearningSettingDto ELearningSettingDto, MultipartFile logoImage, MultipartFile coverImage){
        LogoImage createLogoImage = eLearningImageService.createLogoImage(logoImage);
        CoverImage createCoverImage = eLearningImageService.createCoverImage(coverImage);
        ELearningSetting eLearningSetting = ELearningSetting.createELearningSetting(ELearningSettingDto, createLogoImage, createCoverImage);
        eLearningSettingRepository.save(eLearningSetting);
    }

    @Transactional
    public void eLearningContentsCreate(ELearningContentsDto eLearningContentsDto, List<MultipartFile> menuImage) {

        List<ELearningCategory> eLearningCategories = new ArrayList<>();

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

        // 3번 ELearningCategory 생성 및 해당 하는 ELearningMenu 생성
       for (ELearningCategoryDto eLearningCategoryDto : eLearningContentsDto.getELearningCategoryDtos()) {
           int menuSequence = 1;

           //Content에 Category 연결
           ELearningCategory eLearningCategory = ELearningCategory.createCategory(eLearningCategoryDto, eLearningContent, categorySequence);
           eLearningCategoryRepository.save(eLearningCategory);
           eLearningCategories.add(eLearningCategory);

           //

            /* //메뉴
            for (ELearningMenuDto eLearningMenuDto : eLearningCategoryDto.getMenu()) {
                for(int i = 0; i < eLearningMenuDto.getMenuImageCount(); i++ ){
                    //조건식 수정 필요
                    //이미지 저장 로직
                    //이미지를 저장하고 이미지 n건을 eLeanringMenu에 넣어줍니다.
                    MenuImage createMenuImage = eLearningImageService.createMenuImage(menuImage.get(count));
                    count++;
                }

                ELearningMenu eLearningMenu = ELearningMenu.createManu(eLearningMenuDto, menuSequence);
                ELearningMenu saveELearningMenu = eLearningMenuRepository.save(eLearningMenu);
                //Category와 menu 연걸
                eLearningCategory.setELearningName(saveELearningMenu);
                saveELearningMenu.setELearningCategory(eLearningCategory);

                menuSequence++;
            }*/
           categorySequence++;
           eLearningContent.setELearningCategory(eLearningCategory);
        }
        //eLearningContent.setELearningCategories(eLearningCategories);
    }
}
