package com.example.shortapitest.eLearningApi.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageUploadDto {
    private String upLoadPath;    //이미지 경로
    private String originalFileName; //원본 이미지 이름
    private byte[] fileData;

    public static ImageUploadDto createImageDto(String upLoadPath, MultipartFile image){
        ImageUploadDto imageDto = null;
        try {
            imageDto = ImageUploadDto.builder()
                    .upLoadPath(upLoadPath)
                    .originalFileName(image.getOriginalFilename())
                    .fileData(image.getBytes())
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return imageDto;
    }
}
