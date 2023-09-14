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
import com.example.shortapitest.eLearningApi.repository.image.CoverImageRepository;
import com.example.shortapitest.eLearningApi.repository.image.LogoImageRepository;
import com.example.shortapitest.eLearningApi.repository.image.MenuImageRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;

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


    private final CoverImageRepository coverImageRepository;
    private final LogoImageRepository logoImageRepository;
    private final MenuImageRepository menuImageRepository;


    @Transactional
    public void eLearningSettingCreate(ELearningSettingDto ELearningSettingDto, MultipartFile logoImage, MultipartFile coverImage){

        String logoOriImageName = logoImage.getOriginalFilename();
        String coverOriImageName = coverImage.getOriginalFilename();

        try {
            //파일 업로드
            String newLogoImageName = uploadFile(logoImageLocation, logoOriImageName, logoImage.getBytes());
            LogoImage createLogoImage = LogoImage.setLogoImage(newLogoImageName, logoOriImageName, logoImageLocation);
            logoImageRepository.save(createLogoImage);

            String newCoverImageName = uploadFile(coverImageLocation, logoOriImageName, logoImage.getBytes());
            CoverImage createCoverImage = CoverImage.setCoverImage(newCoverImageName, coverOriImageName, coverImageLocation);
            coverImageRepository.save(createCoverImage);

            ELearningSetting eLearningSetting = ELearningSetting.createELearningSetting(ELearningSettingDto, createLogoImage, createCoverImage);
            eLearningSettingRepository.save(eLearningSetting);
        }
        catch (Exception e){
            e.printStackTrace();
        }
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

                for(int i = 0; i < eLearningMenuDto.getMenuImageCount(); i++ ){
                    //이미지 저장 로직
                    //이미지를 저장하고 이미지 n건을 eLeanringMenu에 넣어줍니다.
                    String oriMenuImageName = menuImage.get(count).getOriginalFilename();
                    try {
                        //파일 업로드
                        if(!StringUtils.isEmpty(oriMenuImageName)){//이름이 있으면 업로드
                            String newMenuImageName = uploadFile(questionImageLocation, oriMenuImageName, menuImage.get(count).getBytes());
                            MenuImage saveMenuImage = MenuImage.setMenuImage(newMenuImageName, oriMenuImageName, questionImageLocation, eLearningMenu, (long) i+1);
                            menuImageRepository.save(saveMenuImage);
                        }
                    }
                    catch (Exception e){
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

        // 1번 이미지 생성 및 문제 생성
        System.out.println(eLearningQuestionDto.toString());





        for (ELearningQuestionSetDto eLearningQuestionSetDto : eLearningQuestionDto.getELearningQuestionSetDtos()){

            ELearningQuestion eLearningQuestion = ELearningQuestion.setELearningQuestion(eLearningQuestionSetDto);
        }

        // 선택 항목을 등록




    }

    // 이미지 저장 및 새로운 이미지 이름을 만듭니다.
    public String uploadFile(String upLoadPath, String originalFileName,byte[] fileData) throws Exception{
        UUID uuid = UUID.randomUUID();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String savedFileName=uuid.toString()+extension;

        String fileUploadFullUrl= upLoadPath+"/"+savedFileName;

        FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);

        fos.write(fileData);

        fos.close();

        return savedFileName;
    }

}
