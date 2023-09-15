package com.example.shortapitest.eLearningApi.service;

import com.example.shortapitest.eLearningApi.dto.request.create.*;
import com.example.shortapitest.eLearningApi.dto.request.update.ELContentsUpdateDto;
import com.example.shortapitest.eLearningApi.dto.response.PageELSettingReturnDto;
import com.example.shortapitest.eLearningApi.dto.response.ELSettingReturnDto;
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
import org.springframework.data.domain.PageImpl;
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
    public void eLearningSettingCreate(ELSettingCreateDto ELearningSettingDto, MultipartFile logoImage, MultipartFile coverImage) {

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
    public void eLearningContentsCreate(ELContentsCreateDto eLearningContentsDto, List<MultipartFile> menuImage) {

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
        for (ELCategoryCreateDto eLearningCategoryDto : eLearningContentsDto.getELearningCategoryDtos()) {
            int menuSequence = 1;
            //Content에 Category 연결
            ELearningCategory eLearningCategory = ELearningCategory.createCategory(eLearningCategoryDto, eLearningContent, categorySequence);
            eLearningCategoryRepository.save(eLearningCategory);
            eLearningContent.setELearningCategory(eLearningCategory);

            //메뉴
            for (ELMenuCreateDto eLearningMenuDto : eLearningCategoryDto.getMenu()) {

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
                for (ELChoiceCreateDto choiceDto: questionDto.getChoiceDtoList()){
                    eLearningQuestion.setChoice(eLearningChoiceRepository.save(ELearningChoice.createELearningChoice(choiceDto, eLearningQuestion, count)));
                }
            }
        );
    }

    public PageELSettingReturnDto selectELearningSettingPage(Pageable pageable) {

        PageImpl<ELearningSetting> eLearningSettingResult = eLearningSettingRepository.selectELearningSetting(pageable);

        PageELSettingReturnDto pageELearningSetting = PageELSettingReturnDto.createPageELearningSetting(eLearningSettingResult);

        eLearningSettingResult.getContent().forEach(
                dto ->{
                    pageELearningSetting.addResponseSetting(ELSettingReturnDto.createResponseSetting(dto));
                }
        );

        return pageELearningSetting;
    }

    @Transactional
    public void eLearningSettingUpdate(ELSettingCreateDto eLearningSettingDto, MultipartFile logoImage, MultipartFile coverImage) {
        Long eLearningSettingId = eLearningSettingDto.getELearningSettingId();

        ELearningSetting eLearningSetting = eLearningSettingRepository.findById(eLearningSettingId).orElseThrow(() -> new EntityNotFoundException("해당하는 ELearning이 없습니다."));
        LogoImage existingLogoImage = logoImageRepository.findById(eLearningSettingId).orElseThrow(() -> new EntityNotFoundException("ELearning에 이미지가 존재하지 않습니다."));
        CoverImage existingCoverImage = coverImageRepository.findById(eLearningSettingId).orElseThrow(() -> new EntityNotFoundException("ELearning에 이미지가 존재하지 않습니다."));

        //이미지 삭제
        try {
            ImageUpload.deleteFile(existingLogoImage.getFileUrl(), existingLogoImage.getFilename());
            ImageUpload.deleteFile(existingCoverImage.getFileUrl(), existingCoverImage.getFilename());

            //파일 업로드
            String newLogoImageName = ImageUpload.uploadFile((ImageUploadDto.createImageDto(logoImageLocation, logoImage)));
            existingLogoImage.updateLogoImage(newLogoImageName, logoImage.getOriginalFilename());

            String newCoverImageName = ImageUpload.uploadFile((ImageUploadDto.createImageDto(coverImageLocation, coverImage)));
            CoverImage createCoverImage = CoverImage.createCoverImage(newCoverImageName, coverImage.getOriginalFilename(), coverImageLocation);
            coverImageRepository.save(createCoverImage);

            eLearningSetting.updateELearningSetting(eLearningSettingDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void eLearningContentsUpdate(ELContentsUpdateDto elContentsUpdateDto, List<MultipartFile> menuImageList) {
        //로직 순서

        //id의 값으로 Contents 확인.
        ELearningContent eLearningContent = eLearningContentRepository
                .findById(elContentsUpdateDto.getELearningContentId()).orElseThrow(() -> new RuntimeException("해당하는 eLearningContents가 없습니다."));

        // 예상 시나리오
        // 카테고리 순서를 변경하거나 메뉴의 순서를 변경하거나 이미지의 순서 변경
        // 또는 메뉴 삭제 또는 메뉴 생성의 경우가 있다.
        // 수정의 개념이 있지만 행삭제 이미지 삭제등이 내장되어 있다.

        //프론트에서 삭제 되는 항목의 pk 값을 추가로 받아옵니다.

/*^

        eLearningContentsDto.getELearningCategoryDtos().forEach(
                //여기서 행해지는 로직 카테고리 값을 수정
                //순차적으로 데이터 베이스에 있는 카테고리 값을 가져옵니다.
                elearningCategoryDto -> {
                    // 사이즈 값으로 카테고리의 마지막 순번 값을 알 수 있음으로 마지막 순번 이상의 카테고리 시퀀스 값을 가지는 카테고리 들은 모두 지워버려야합니다.
                    if (elearningCategoryDto.) {

                    }

                    // eLearningContent id 와 시퀀스가 1번인 것부터 순차적으로 데이터를 가져오는 로직 필요.
                    // eLearningCategoryDtos가 1번부터 순차적으로 들어온다고 가정하였을때
                    ELearningCategory eLearningCategory = eLearningCategoryRepository.findByCategory(eLearningContent.getId(), elearningCategoryDto.getCategorySequence());
                }
        );
*/
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
