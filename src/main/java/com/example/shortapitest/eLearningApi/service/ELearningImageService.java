package com.example.shortapitest.eLearningApi.service;

import com.example.shortapitest.eLearningApi.entity.image.CoverImage;
import com.example.shortapitest.eLearningApi.entity.image.LogoImage;
import com.example.shortapitest.eLearningApi.repository.image.CoverImageRepository;
import com.example.shortapitest.eLearningApi.repository.image.LogoImageRepository;
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

    private final LogoImageRepository logoImageRepository;

    private final CoverImageRepository coverImageRepository;

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
}
