package com.example.shortapitest.eLearningApi.service;

import com.example.shortapitest.eLearningApi.dto.request.*;
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
import com.example.shortapitest.eLearningApi.repository.queryDsl.ELQuery;
import com.example.shortapitest.eLearningApi.utill.ImageUpload;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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

    private final ELQuery elQuery;

    @Transactional
    public void eLearningSettingCreate(ELSettingDto elSettingDto, MultipartFile logoImage, MultipartFile coverImage) {

        //파일 업로드
        String newLogoImageName = ImageUpload.uploadFile((ImageUploadDto.createImageDto(logoImageLocation, logoImage)));
        LogoImage createLogoImage = LogoImage.createLogoImage(newLogoImageName, logoImage.getOriginalFilename(), logoImageLocation);
        logoImageRepository.save(createLogoImage);

        String newCoverImageName = ImageUpload.uploadFile((ImageUploadDto.createImageDto(coverImageLocation, coverImage)));
        CoverImage createCoverImage = CoverImage.createCoverImage(newCoverImageName, coverImage.getOriginalFilename(), coverImageLocation);
        coverImageRepository.save(createCoverImage);

        ELearningSetting eLearningSetting = ELearningSetting.createSetting(elSettingDto, createLogoImage, createCoverImage);
        eLearningSettingRepository.save(eLearningSetting);
    }

    @Transactional
    public void eLearningContentsCreate(ELContentsDto elContentsDto, List<MultipartFile> menuImageList) {

        // 1번 Dto에서 id 값을 추출하여 ELearningSetting에 해당하는 값이 있는지 조회
        ELearningSetting eLearningSetting = eLearningSettingRepository.findById(elContentsDto.getELSettingId()).orElseThrow(() -> new EntityNotFoundException("해당하는 ELearning이 없습니다."));

        // 2번 ELearningContent 생성
        ELearningContent eLearningContent = ELearningContent.createELearningContent(eLearningSetting);
        eLearningContentRepository.save(eLearningContent);
        //Setting에 Content 연결
        eLearningSetting.setELearningContent(eLearningContent);

        // 3번 ELearningCategory 생성 및 해당 하는 ELearningMenu 생성
        // 카테고리를 분해하고
        // 카테고리 생성
        // 메뉴를 생성하고 메뉴에 대한 정보를 카테고리 안에 넣어주는 작업

        elContentsDto.getELCategoryDtoList().forEach(categoryDto -> {
            ELearningCategory eLearningCategory = ELearningCategory.createCategory(categoryDto, eLearningContent);
            eLearningCategoryRepository.save(eLearningCategory);
            eLearningContent.setELearningCategory(eLearningCategory);

            categoryDto.getMenuDtoList().forEach(menuDto -> {
                ELearningMenu eLearningMenu = ELearningMenu.createMenu(menuDto, eLearningCategory);
                eLearningMenuRepository.save(eLearningMenu);
                //Category와 menu 연걸
                eLearningCategory.addELearningMenuList(eLearningMenu);

                for (int i = 0; i < menuDto.getMenuImageCount(); i++) {
                    //이미지 저장 로직
                    //이미지를 저장하고 이미지 n건을 eLeanringMenu에 넣어줍니다.
                    MultipartFile menuImage = menuImageList.get(0);
                        //파일 업로드
                    String newMenuImageName = ImageUpload.uploadFile((ImageUploadDto.createImageDto(menuImageLocation, menuImage)));
                    MenuImage saveMenuImage = MenuImage.createMenuImage(newMenuImageName, menuImage.getOriginalFilename(), menuImageLocation, eLearningMenu, i);
                    menuImageRepository.save(saveMenuImage);
                    menuImageList.remove(0);
                }
            });
        });
    }

    @Transactional
    public void eLearningQuestionCreate(ELQuestionDto elQuestionDto, List<MultipartFile> questionImages) {

        //해당하는 이러닝에 대한 정보를 가져옵니다.
        ELearningSetting eLearningSetting = eLearningSettingRepository.findById(elQuestionDto.getELSettingId()).orElseThrow(() -> new EntityNotFoundException("해당하는 ELearning이 없습니다."));

        // Question 생성후 이미지 검사 로직을 통하여 이미지 저장 여부를 확인합니다.
        elQuestionDto.getElQuestionDetailDtoList().forEach(questionDto -> {
            ELearningQuestion eLearningQuestion = eLearningQuestionRepository.save(ELearningQuestion.createELearningQuestion(questionDto, eLearningSetting));
            eLearningSetting.addQuestion(eLearningQuestion);
            if (questionDto.isQuestionImageCheck()) {
                MultipartFile questionImage = questionImages.get(0);
                String newQuestionImageName = ImageUpload.uploadFile((ImageUploadDto.createImageDto(questionImageLocation, questionImage)));
                QuestionImage saveQuestionImage = QuestionImage.createLogoImage(newQuestionImageName, questionImage.getOriginalFilename(), questionImageLocation, eLearningQuestion);
                questionImageRepository.save(saveQuestionImage);
                eLearningQuestion.setQuestionImage(saveQuestionImage);
                questionImages.remove(0);
            }
            for (ELChoiceDto choiceDto : questionDto.getChoiceDtoList()) {
                eLearningQuestion.addChoice(eLearningChoiceRepository.save(ELearningChoice.createELearningChoice(choiceDto, eLearningQuestion)));
            }
        });
    }

    // stream 사용
    // 검색 기능 추가
    public PageReturnDto selectELearningSettingPage(Pageable pageable, String createDate, String endDate, String keyword) {
        PageReturnDto pageReturnDto = new PageReturnDto(ELSettingReturnDto.createResponseSetting(eLearningSettingRepository.selectELearningSetting(pageable, createDate, endDate, keyword)));
        return pageReturnDto;
    }

    public ELContentsReturnDto selectELearningContent(Long eLSettingId) {

        ELearningContent eLearningContent = elQuery.findBySetting(eLSettingId);
        List<ELearningCategory> eLearningCategories = elQuery.findByContentId(eLearningContent.getId());
        List<ELCategoryReturnDto> dtoList = eLearningCategories.stream().map(ELCategoryReturnDto::setELCategoryReturnDto).toList();

        return new ELContentsReturnDto(eLSettingId, dtoList);
    }

    public ELQuestionReturnDto selectELearningQuestion(Long eLSettingId) {

        //해당하는 이러닝에 대한 정보를 가져옵니다.
        ELearningSetting eLearningSetting = eLearningSettingRepository.findById(eLSettingId).orElseThrow(() -> new EntityNotFoundException("해당하는 ELearning이 없습니다."));

        List<ELearningQuestion> eLearningQuestionList = elQuery.findByQuestionId(eLearningSetting.getId());

        List<ELQuestionDetailReturnDto> elQuestionDetailReturnDtos = eLearningQuestionList.stream().map(ELQuestionDetailReturnDto::setQuestionDetailReturnDto).toList();
        return new ELQuestionReturnDto(elQuestionDetailReturnDtos, eLearningSetting);
    }

    @Transactional
    public void eLearningSettingUpdate(ELSettingDto elSettingDto, MultipartFile logoImage, MultipartFile coverImage) {
        Long eLearningSettingId = elSettingDto.getELSettingId();

        ELearningSetting eLearningSetting = eLearningSettingRepository.findById(eLearningSettingId).orElseThrow(() -> new EntityNotFoundException("해당하는 ELearning이 없습니다."));
        LogoImage existingLogoImage = logoImageRepository.findById(eLearningSettingId).orElseThrow(() -> new EntityNotFoundException("ELearning에 이미지가 존재하지 않습니다."));
        CoverImage existingCoverImage = coverImageRepository.findById(eLearningSettingId).orElseThrow(() -> new EntityNotFoundException("ELearning에 이미지가 존재하지 않습니다."));

        //이미지 삭제
        ImageUpload.deleteFile(existingLogoImage.getFileUrl(), existingLogoImage.getFilename());
        ImageUpload.deleteFile(existingCoverImage.getFileUrl(), existingCoverImage.getFilename());

        //파일 업로드
        String newLogoImageName = ImageUpload.uploadFile((ImageUploadDto.createImageDto(logoImageLocation, logoImage)));
        existingLogoImage.updateLogoImage(newLogoImageName, logoImage.getOriginalFilename());

        String newCoverImageName = ImageUpload.uploadFile((ImageUploadDto.createImageDto(coverImageLocation, coverImage)));
        existingCoverImage.updateCoverImage(newCoverImageName, coverImage.getOriginalFilename());

        eLearningSetting.updateSetting(elSettingDto);
    }

    @Transactional
    public void eLearningContentsUpdate(ELContentsDto elContentsDto, List<MultipartFile> menuImageList) {

        //id의 값으로 Contents 확인.
        ELearningContent eLearningContent = eLearningContentRepository.findById(elContentsDto.getELContentsId()).orElseThrow(() -> new RuntimeException("해당하는 eLearningContents가 없습니다."));

        // 1번 기존 카테고리에서 삭제되는 항목이 존재하면 삭제
        elContentsDto.getDeleteCategoryId().forEach(categoryId -> {
            List<ELearningMenu> eLearningMenuList = elQuery.findByMenuId(categoryId);
            eLearningMenuList.forEach(menu -> menu.getMenuImageList().forEach(menuImage -> {
                    ImageUpload.deleteFile(menuImage.getFileUrl(), menuImage.getFilename());
            }));
            eLearningCategoryRepository.deleteById(categoryId);
        });
        //카테고리가 아닌 메뉴 단일 삭제시
        elContentsDto.getELCategoryDtoList().forEach(updateCategoryDto -> {
            updateCategoryDto.getDeleteMenuId().forEach(menuId -> {
                List<MenuImage> deleteMenuImageList = menuImageRepository.findByMenuId(menuId);
                deleteMenuImageList.forEach(
                    menuImage -> {
                        ImageUpload.deleteFile(menuImage.getFileUrl(), menuImage.getFilename());
                    }
                );
                eLearningMenuRepository.deleteById(menuId);
            });
        });
        //여기서 부턴 생성 또는 수정 로직
        elContentsDto.getELCategoryDtoList().forEach(
            categoryDto -> {
                if (categoryDto.getCategoryId() == null) {//카테고리 생성로직
                    ELearningCategory eLearningCategory = ELearningCategory.createCategory(categoryDto, eLearningContent);
                    eLearningCategoryRepository.save(eLearningCategory);
                    eLearningContent.setELearningCategory(eLearningCategory);

                    //매뉴 생성
                    categoryDto.getMenuDtoList().forEach( menuDto -> {
                        ELearningMenu eLearningMenu = ELearningMenu.createMenu(menuDto, eLearningCategory);
                        eLearningMenuRepository.save(eLearningMenu);
                        //Category와 menu 연걸
                        eLearningCategory.addELearningMenuList(eLearningMenu);
                        for (int i = 0; i < menuDto.getMenuImageCount(); i++) {
                            //이미지 저장 로직
                            //이미지를 저장하고 이미지 n건을 eLeanringMenu에 넣어줍니다.
                            MultipartFile menuImage = menuImageList.get(0);
                            //파일 업로드
                            String newMenuImageName = ImageUpload.uploadFile((ImageUploadDto.createImageDto(menuImageLocation, menuImage)));
                            MenuImage saveMenuImage = MenuImage.createMenuImage(newMenuImageName, menuImage.getOriginalFilename(), menuImageLocation, eLearningMenu, i);
                            menuImageRepository.save(saveMenuImage);
                            menuImageList.remove(0);
                        }
                    });
                } else {//카테고리 업데이트 로직
                    ELearningCategory eLearningCategory = eLearningCategoryRepository.findById(categoryDto.getCategoryId()).orElseThrow(() -> new RuntimeException("해당하는 eLearningCategoru가 없습니다."));
                    eLearningCategory.updateCategory(categoryDto);

                    categoryDto.getMenuDtoList().forEach(
                        menuDto -> {
                            menuDto.getDeleteImageId().forEach(
                                imageId ->{
                                    MenuImage deleteMenuImageList = menuImageRepository.findById(imageId).orElseThrow(() -> new RuntimeException("해당는 이미지가 없습니다."));
                                    ImageUpload.deleteFile(deleteMenuImageList.getFileUrl(), deleteMenuImageList.getFilename());
                                    menuImageRepository.deleteById(imageId);
                                }
                            );
                            ELearningMenu eLearningMenu = eLearningMenuRepository.findById(menuDto.getMenuId()).orElseThrow(() -> new RuntimeException("해당하는 eLearningMenu가 없습니다."));
                            eLearningMenu.updateManu(menuDto);

                            for (int i = 0; i < menuDto.getUpdateImageId().size(); i++ ) {
                                MenuImage updateMenuImage = menuImageRepository.findById(menuDto.getUpdateImageId().get(i)).orElseThrow(() -> new RuntimeException("해당하는 이미지가 없습니다."));
                                String newMenuImageName = "";
                                newMenuImageName = ImageUpload.uploadFile((ImageUploadDto.createImageDto(menuImageLocation, menuImageList.get(0))));
                                updateMenuImage.updateMenuImage(newMenuImageName, menuImageList.get(0).getOriginalFilename() , i);
                                menuImageList.remove(0);
                            }
                        }
                    );
                }
            }
        );
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
