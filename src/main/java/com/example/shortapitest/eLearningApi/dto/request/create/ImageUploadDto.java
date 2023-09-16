package com.example.shortapitest.eLearningApi.dto.request.create;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Getter
@Setter
public class ImageUploadDto {

    private String upLoadPath;    //이미지 경로
    private String originalFileName; //원본 이미지 이름
    private byte[] fileData;

    public static ImageUploadDto createImageDto(String upLoadPath, MultipartFile image){
        ImageUploadDto imageDto = new ImageUploadDto();
        try {
            imageDto.setUpLoadPath(upLoadPath);
            imageDto.setOriginalFileName(image.getOriginalFilename());
            imageDto.setFileData(image.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return imageDto;
    }
}
