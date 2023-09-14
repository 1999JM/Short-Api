package com.example.shortapitest.eLearningApi.service;

import com.example.shortapitest.eLearningApi.dto.requestDto.*;
import com.example.shortapitest.eLearningApi.dto.responseDto.ResponseSetting;
import com.example.shortapitest.eLearningApi.entity.eLearning.ELearningSetting;
import com.example.shortapitest.eLearningApi.entity.eLearning.content.ELearningCategory;
import com.example.shortapitest.eLearningApi.entity.eLearning.content.ELearningContent;
import com.example.shortapitest.eLearningApi.entity.eLearning.content.ELearningMenu;
import com.example.shortapitest.eLearningApi.entity.eLearning.question.ELearningChoice;
import com.example.shortapitest.eLearningApi.entity.eLearning.question.ELearningQuestion;
import com.example.shortapitest.eLearningApi.entity.image.CoverImage;
import com.example.shortapitest.eLearningApi.entity.image.LogoImage;
import com.example.shortapitest.eLearningApi.entity.image.MenuImage;
import com.example.shortapitest.eLearningApi.entity.image.QuestionImage;
import com.example.shortapitest.eLearningApi.repository.eLearning.*;
import com.example.shortapitest.eLearningApi.repository.image.CoverImageRepository;
import com.example.shortapitest.eLearningApi.repository.image.LogoImageRepository;
import com.example.shortapitest.eLearningApi.repository.image.MenuImageRepository;
import com.example.shortapitest.eLearningApi.repository.image.QuestionImageRepository;
import com.example.shortapitest.eLearningApi.utill.ImageUpload;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ELearningService {

    @Value("${logoImage}")
    private String logoImageLocation;
    @Value("${coverImage}")
    private String coverImageLocation;
    @Value("${menuImage}")
    private String menuImageLocation;
    @Value("${questionImage}")
    private String questionImageLocation;
    private final ELearningSettingRepository eLearningSettingRepository;
    private final ELearningContentRepository eLearningContentRepository;
    private final ELearningCategoryRepository eLearningCategoryRepository;
    private final ELearningMenuRepository eLearningMenuRepository;
    private final ELearningQuestionRepository eLearningQuestionRepository;
    private final ELearningChoiceRepository eLearningChoiceRepository;
    private final CoverImageRepository coverImageRepository;
    private final LogoImageRepository logoImageRepository;
    private final MenuImageRepository menuImageRepository;
    private final QuestionImageRepository questionImageRepository;

    @Transactional
    public void eLearningSettingCreate(ELearningSettingDto ELearningSettingDto, MultipartFile logoImage, MultipartFile coverImage) {

        try {
            //파일 업로드
            String newLogoImageName = ImageUpload.uploadFile((ImageUploadDto.createImageDto(logoImageLocation, logoImage)));
            LogoImage createLogoImage = LogoImage.createLogoImage(newLogoImageName, logoImage.getOriginalFilename(), logoImageLocation);
            logoImageRepository.save(createLogoImage);

            String newCoverImageName = ImageUpload.uploadFile((ImageUploadDto.createImageDto(coverImageLocation, coverImage)));
            CoverImage createCoverImage = CoverImage.createCoverImage(newCoverImageName, coverImage.getOriginalFilename(), coverImageLocation);
            coverImageRepository.save(createCoverImage);

            ELearningSetting eLearningSetting = ELearningSetting.createELearningSetting(ELearningSettingDto, createLogoImage, createCoverImage);
            eLearningSettingRepository.save(eLearningSetting);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void eLearningContentsCreate(ELearningContentsDto eLearningContentsDto, List<MultipartFile> menuImage) {

        int categorySequence = 1;
        int count = 0;

        // 1번 Dto에서 id 값을 추출하여 ELearningSetting에 해당하는 값이 있는지 조회
        ELearningSetting eLearningSetting = eLearningSettingRepository.findById(eLearningContentsDto.getELearningId()).orElseThrow(() -> new EntityNotFoundException("해당하는 ELearning이 없습니다."));

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
            eLearningContent.setELearningCategory(eLearningCategory);

            //메뉴
            for (ELearningMenuDto eLearningMenuDto : eLearningCategoryDto.getMenu()) {

                ELearningMenu eLearningMenu = ELearningMenu.createManu(eLearningMenuDto, menuSequence, eLearningCategory);
                eLearningMenuRepository.save(eLearningMenu);

                //Category와 menu 연걸
                eLearningCategory.setELearningMenu(eLearningMenu);

                for (int i = 0; i < eLearningMenuDto.getMenuImageCount(); i++) {
                    //이미지 저장 로직
                    //이미지를 저장하고 이미지 n건을 eLeanringMenu에 넣어줍니다.
                    String oriMenuImageName = menuImage.get(count).getOriginalFilename();
                    try {
                        //파일 업로드
                        if (!StringUtils.isEmpty(oriMenuImageName)) {//이름이 있으면 업로드
                            String newMenuImageName = ImageUpload.uploadFile((ImageUploadDto.createImageDto(questionImageLocation, menuImage.get(count))));
                            MenuImage saveMenuImage = MenuImage.createMenuImage(newMenuImageName, menuImage.get(count).getOriginalFilename(), questionImageLocation, eLearningMenu, (long) i + 1);
                            menuImageRepository.save(saveMenuImage);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    count++;
                }
                menuSequence++;
            }
            categorySequence++;
        }
    }

    @Transactional
    public void eLearningQuestionCreate(ELearningQuestionDto eLearningQuestionDto, List<MultipartFile> questionImages) {
        //해당하는 이러닝에 대한 정보를 가져옵니다.
        ELearningSetting eLearningSetting = eLearningSettingRepository.findById(eLearningQuestionDto.getELearningId()).orElseThrow(() -> new EntityNotFoundException("해당하는 ELearning이 없습니다."));

        // Question 생성후 이미지 검사 로직을 통하여 이미지 저장 여부를 확인합니다.
        eLearningQuestionDto.getELearningQuestionSetDtos().forEach(questionDto -> {
            ELearningQuestion eLearningQuestion = eLearningQuestionRepository.save(ELearningQuestion.createELearningQuestion(questionDto, eLearningSetting));
            eLearningSetting.addQuestion(eLearningQuestion);
                if (questionDto.isQuestionImage()) {
                    MultipartFile questionImage = questionImages.get(0);
                    try {
                        String newQuestionImageName = ImageUpload.uploadFile((ImageUploadDto.createImageDto(questionImageLocation, questionImage)));
                        QuestionImage saveQuestionImage = QuestionImage.createLogoImage(newQuestionImageName, questionImage.getOriginalFilename(), questionImageLocation, eLearningQuestion);
                        questionImageRepository.save(saveQuestionImage);
                        eLearningQuestion.setQuestionImage(saveQuestionImage);
                        questionImages.remove(0);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                int count = 1;
                for (ELearningChoiceDto choiceDto: questionDto.getChoiceDtoList()){
                    eLearningQuestion.setChoice(eLearningChoiceRepository.save(ELearningChoice.createELearningChoice(choiceDto, eLearningQuestion, count)));
                }
            }
        );
    }

    public void selectELearningSettingPage(Pageable pageable) {

        Page<ELearningSetting> responseSettings = eLearningSettingRepository.selectELearningSetting(pageable);
        System.out.println(responseSettings.getTotalPages());
        responseSettings.getContent().forEach(dto -> {
            System.out.println(dto.toString());
        });
    }

    @Transactional
    public void eLearningSettingDelete(long eLearningSettingId) {
        //해당하는 이러닝에 대한 정보를 가져옵니다.
        ELearningSetting eLearningSetting = eLearningSettingRepository.findById(eLearningSettingId).orElseThrow(() -> new EntityNotFoundException("해당하는 ELearning이 없습니다."));
        eLearningSetting.setDeletedTrue();
    }

    @Transactional
    public void eLearningSettingRecovery(long eLearningSettingId) {
        //해당하는 이러닝에 대한 정보를 가져옵니다.
        ELearningSetting eLearningSetting = eLearningSettingRepository.findById(eLearningSettingId).orElseThrow(() -> new EntityNotFoundException("해당하는 ELearning이 없습니다."));
        eLearningSetting.setDeletedFalse();
    }
}
