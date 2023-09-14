package com.example.shortapitest.eLearningApi.utill;

import com.example.shortapitest.eLearningApi.dto.requestDto.ImageUploadDto;

import java.io.FileOutputStream;
import java.util.UUID;

public class ImageUpload {
    public static String uploadFile(ImageUploadDto imageUploadDto) throws Exception{

        String originalFileName = imageUploadDto.getOriginalFileName();

        //구조 변경
        UUID uuid = UUID.randomUUID();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String savedFileName=uuid.toString()+extension;

        String fileUploadFullUrl= imageUploadDto.getUpLoadPath()+"/"+savedFileName;

        FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);

        fos.write(imageUploadDto.getFileData());

        fos.close();

        return savedFileName;
    }
}
