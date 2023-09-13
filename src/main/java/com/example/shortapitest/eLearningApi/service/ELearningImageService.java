package com.example.shortapitest.eLearningApi.service;

import com.example.shortapitest.eLearningApi.entity.image.CoverImage;
import com.example.shortapitest.eLearningApi.entity.image.LogoImage;
import com.example.shortapitest.eLearningApi.entity.image.MenuImage;
import com.example.shortapitest.eLearningApi.repository.image.CoverImageRepository;
import com.example.shortapitest.eLearningApi.repository.image.LogoImageRepository;
import com.example.shortapitest.eLearningApi.repository.image.MenuImageRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ELearningImageService {

    @Value("${logoImage}")
    private String logoImageLocation;

    @Value("${coverImage}")
    private String coverImageLocation;

    @Value("${menuImage}")
    private String menuImageLocation;

    private final LogoImageRepository logoImageRepository;

    private final CoverImageRepository coverImageRepository;

    private final MenuImageRepository menuImageRepository;

    private final FileUploadService fileUploadService;

    public LogoImage createLogoImage(MultipartFile logoImage) {

        LogoImage saveLogoImage = new LogoImage();
        String oriImageName = logoImage.getOriginalFilename();

        try {
            //파일 업로드
            if(!StringUtils.isEmpty(oriImageName)){//이름이 있으면 업로드
                String newImageName = fileUploadService.uploadFile(logoImageLocation, oriImageName, logoImage.getBytes());
                String imageUrl = logoImageLocation + oriImageName;

                saveLogoImage = saveLogoImage.setLogoImage(newImageName, oriImageName, imageUrl);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return logoImageRepository.save(saveLogoImage);
    }

    public CoverImage createCoverImage(MultipartFile coverImage) {

        CoverImage saveCoverImage = new CoverImage();
        String oriImageName = coverImage.getOriginalFilename();

        try {
            //파일 업로드
            if(!StringUtils.isEmpty(oriImageName)){//이름이 있으면 업로드
                String newImageName = fileUploadService.uploadFile(coverImageLocation, oriImageName, coverImage.getBytes());
                String imageUrl = coverImageLocation + oriImageName;
                saveCoverImage = saveCoverImage.setCoverImage(newImageName, oriImageName, imageUrl);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return coverImageRepository.save(saveCoverImage);
    }

    public MenuImage createMenuImage(MultipartFile menuImage) {

        MenuImage saveMenuImage = new MenuImage();
        String oriImageName = menuImage.getOriginalFilename();

        try {
            //파일 업로드
            if(!StringUtils.isEmpty(oriImageName)){//이름이 있으면 업로드
                String newImageName = fileUploadService.uploadFile(menuImageLocation, oriImageName, menuImage.getBytes());
                String imageUrl = menuImageLocation + oriImageName;
                saveMenuImage = MenuImage.setMenuImage(newImageName, oriImageName, imageUrl);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return menuImageRepository.save(saveMenuImage);
    }
}
