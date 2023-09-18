package com.example.shortapitest.eLearningApi.service;

import com.example.shortapitest.eLearningApi.dto.request.create.*;
import com.example.shortapitest.eLearningApi.dto.request.update.ELContentsUpdateDto;
import com.example.shortapitest.eLearningApi.dto.request.update.ELSettingUpdateDto;
import com.example.shortapitest.eLearningApi.dto.response.*;
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
    public void eLearningSettingCreate(ELSettingCreateDto eLSettingCreateDto, MultipartFile logoImage, MultipartFile coverImage) {

        try {
            //파일 업로드
            String newLogoImageName = ImageUpload.uploadFile((ImageUploadDto.createImageDto(logoImageLocation, logoImage)));
            LogoImage createLogoImage = LogoImage.createLogoImage(newLogoImageName, logoImage.getOriginalFilename(), logoImageLocation);
            logoImageRepository.save(createLogoImage);

            String newCoverImageName = ImageUpload.uploadFile((ImageUploadDto.createImageDto(coverImageLocation, coverImage)));
            CoverImage createCoverImage = CoverImage.createCoverImage(newCoverImageName, coverImage.getOriginalFilename(), coverImageLocation);
            coverImageRepository.save(createCoverImage);

            ELearningSetting eLearningSetting = ELearningSetting.createELearningSetting(eLSettingCreateDto, createLogoImage, createCoverImage);
            eLearningSettingRepository.save(eLearningSetting);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void eLearningContentsCreate(ELContentsCreateDto eLContentsCreateDto, List<MultipartFile> menuImageList) {

        // 1번 Dto에서 id 값을 추출하여 ELearningSetting에 해당하는 값이 있는지 조회
        ELearningSetting eLearningSetting = eLearningSettingRepository.findById(eLContentsCreateDto.getELSettingId()).orElseThrow(() -> new EntityNotFoundException("해당하는 ELearning이 없습니다."));

        // 2번 ELearningContent 생성
        ELearningContent eLearningContent = ELearningContent.createELearningContent(eLearningSetting);
        eLearningContentRepository.save(eLearningContent);
        //Setting에 Content 연결
        eLearningSetting.setELearningContent(eLearningContent);

        // 3번 ELearningCategory 생성 및 해당 하는 ELearningMenu 생성
        // 카테고리를 분해하고
        // 카테고리 생성
        // 메뉴를 생성하고 메뉴에 대한 정보를 카테고리 안에 넣어주는 작업
        // 코드 간략화

        eLContentsCreateDto.getELCategoryCreateDtoList().forEach(categoryDto -> {
            ELearningCategory eLearningCategory = ELearningCategory.createCategory(categoryDto, eLearningContent);
            eLearningCategoryRepository.save(eLearningCategory);
            eLearningContent.setELearningCategory(eLearningCategory);

            categoryDto.getMenuList().forEach(menuDto -> {
                ELearningMenu eLearningMenu = ELearningMenu.createManu(menuDto, eLearningCategory);
                eLearningMenuRepository.save(eLearningMenu);
                //Category와 menu 연걸
                eLearningCategory.addELearningMenu(eLearningMenu);

                for (int i = 0; i < menuDto.getMenuImageCount(); i++) {
                    //이미지 저장 로직
                    //이미지를 저장하고 이미지 n건을 eLeanringMenu에 넣어줍니다.
                    MultipartFile menuImage = menuImageList.get(0);
                    try {
                        //파일 업로드
                        String newMenuImageName = ImageUpload.uploadFile((ImageUploadDto.createImageDto(menuImageLocation, menuImage)));
                        MenuImage saveMenuImage = MenuImage.createMenuImage(newMenuImageName, menuImage.getOriginalFilename(), menuImageLocation, eLearningMenu, i);
                        menuImageRepository.save(saveMenuImage);
                        menuImageList.remove(0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        });
    }

    @Transactional
    public void eLearningQuestionCreate(ELQuestionCreateDto elQuestionCreateDto, List<MultipartFile> questionImages) {

        //해당하는 이러닝에 대한 정보를 가져옵니다.
        ELearningSetting eLearningSetting = eLearningSettingRepository.findById(elQuestionCreateDto.getELSettingId()).orElseThrow(() -> new EntityNotFoundException("해당하는 ELearning이 없습니다."));

        // Question 생성후 이미지 검사 로직을 통하여 이미지 저장 여부를 확인합니다.
        elQuestionCreateDto.getElQuestionDetailCreateDtoList().forEach(questionDto -> {
            ELearningQuestion eLearningQuestion = eLearningQuestionRepository.save(ELearningQuestion.createELearningQuestion(questionDto, eLearningSetting));
            eLearningSetting.addQuestion(eLearningQuestion);
            if (questionDto.isQuestionImageCheck()) {
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
            for (ELChoiceCreateDto choiceDto : questionDto.getChoiceDtoList()) {
                eLearningQuestion.addChoice(eLearningChoiceRepository.save(ELearningChoice.createELearningChoice(choiceDto, eLearningQuestion)));
            }
        });
    }

    // stream 사용
    // 검색 기능 추가
    public Page<ELSettingReturnDto> selectELearningSettingPage(Pageable pageable, String createDate, String endDate, String keyword) {

        Page<ELSettingReturnDto> elSettingReturnDtoList = ELSettingReturnDto.createResponseSetting(eLearningSettingRepository.selectELearningSetting(pageable, createDate, endDate, keyword));
        return elSettingReturnDtoList;
    }

    public ELContentsReturnDto selectELearningContent(Long eLSettingId) {

        //해당하는 이러닝에 대한 정보를 가져옵니다.
        ELearningSetting eLearningSetting = eLearningSettingRepository.findById(eLSettingId).orElseThrow(() -> new EntityNotFoundException("해당하는 ELearning이 없습니다."));

        ELContentsReturnDto elContentsReturnDto = new ELContentsReturnDto();

        //해당하는 아이디에 콘텐츠를 가져온다
        eLearningSetting.getELearningContent().getELearningCategoryList().forEach(
                categoryDto -> {
                    ELCategoryReturnDto elCategoryReturnDto = ELCategoryReturnDto.setELCategoryReturnDto(categoryDto);
                    categoryDto.getELearningMenuList().forEach(
                            menuDto -> {
                                ELMenuReturnDto elMenuReturnDto = ELMenuReturnDto.setELMenuReturnDto(menuDto);
                                List<MenuImage> menuImageList = menuImageRepository.findByMenuId(menuDto.getId());

                                menuImageList.forEach(menuImage -> elMenuReturnDto.addELImageReturnDto(ELImageReturnDto.setELImageReturnDto(menuImage)));
                                elCategoryReturnDto.addMenuReturnDto(elMenuReturnDto);
                            }
                    );
                    elContentsReturnDto.addELCategoryReturnDtoList(elCategoryReturnDto, eLearningSetting.getId());
                }
        );

        return elContentsReturnDto;
    }

    public ELQuestionReturnDto selectELearningQuestion(Long eLSettingId) {

        //해당하는 이러닝에 대한 정보를 가져옵니다.
        ELearningSetting eLearningSetting = eLearningSettingRepository.findById(eLSettingId).orElseThrow(() -> new EntityNotFoundException("해당하는 ELearning이 없습니다."));
        ELQuestionReturnDto elQuestionReturnDto = ELQuestionReturnDto.setELQuestionReturnDto(eLearningSetting);

        eLearningSetting.getELearningQuestionList().forEach(
                questionDto -> {
                    ELImageReturnDto elImageReturnDto = ELImageReturnDto.setELImageReturnDto(questionImageRepository.findByQuestionId(questionDto.getId()));
                    ELQuestionDetailReturnDto elQuestionDetailReturnDto = ELQuestionDetailReturnDto.setQuestionDetailReturnDto(questionDto, elImageReturnDto);

                    questionDto.getELearningChoiceList().forEach(choiceDto -> elQuestionDetailReturnDto.addELChoiceReturnDto(ELChoiceReturnDto.setELChoiceReturnDto(choiceDto)));
                    elQuestionReturnDto.addELQuestionDetailReturnDto(elQuestionDetailReturnDto);
                }
        );

        return elQuestionReturnDto;
    }

    @Transactional
    public void eLearningSettingUpdate(ELSettingUpdateDto elSettingUpdateDto, MultipartFile logoImage, MultipartFile coverImage) {
        Long eLearningSettingId = elSettingUpdateDto.getELearningSettingId();

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
            existingCoverImage.updateCoverImage(newCoverImageName, coverImage.getOriginalFilename());

            eLearningSetting.updateELearningSetting(elSettingUpdateDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void eLearningContentsUpdate(ELContentsUpdateDto elContentsUpdateDto, List<MultipartFile> menuImageList) {

        //id의 값으로 Contents 확인.
        ELearningContent eLearningContent = eLearningContentRepository.findById(elContentsUpdateDto.getELearningContentId()).orElseThrow(() -> new RuntimeException("해당하는 eLearningContents가 없습니다."));

        // 1번 기존 카테고리에서 삭제되는 항목이 있는지 검사 후 존재하면 삭제
        elContentsUpdateDto.getDeleteCategoryId().forEach(categoryId -> eLearningCategoryRepository.deleteById(categoryId));

        elContentsUpdateDto.getElCategoryUpdateDtoList().forEach(updateCategoryDto -> {
            if (updateCategoryDto.getCategoryId() == null) {
                //카테고리 생성로직
                ELearningCategory eLearningCategory = ELearningCategory.updateDtoCategory(updateCategoryDto, eLearningContent);
                eLearningCategoryRepository.save(eLearningCategory);
                eLearningContent.setELearningCategory(eLearningCategory);
            } else {
                //카테고리 업데이트 로직
                ELearningCategory eLearningCategory = eLearningCategoryRepository.findById(updateCategoryDto.getCategoryId()).orElseThrow(() -> new RuntimeException("해당하는 eLearningCategoru가 없습니다."));
                eLearningCategory.setELearningCategory(updateCategoryDto);
            }

            //메뉴 삭제 이미지 삭제 로직 추후 추가 필요.
            updateCategoryDto.getDeleteMenuId().forEach(menuId -> eLearningMenuRepository.deleteById(menuId));

            //메뉴 추가 및 수정 로직
            updateCategoryDto.getMenuUpdateDtoList().forEach(
                    menuUpdateDto -> {

                    }
            );
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
