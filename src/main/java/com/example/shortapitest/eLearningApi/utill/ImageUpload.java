package com.example.shortapitest.eLearningApi.utill;

import com.example.shortapitest.eLearningApi.dto.request.ImageUploadDto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class ImageUpload {
    public static String uploadFile(ImageUploadDto imageUploadDto) {

        String originalFileName = imageUploadDto.getOriginalFileName();

        UUID uuid = UUID.randomUUID();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String savedFileName=uuid.toString()+extension;

        String fileUploadFullUrl= imageUploadDto.getUpLoadPath()+"/"+savedFileName;

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fileUploadFullUrl);
            fos.write(imageUploadDto.getFileData());
            fos.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
        return savedFileName;
    }

    public static void deleteFile(String path, String fileName) {

        //삭제할 파일 경로 및 파일 이름
        String targetFile = path + fileName;

        File deleteFile = new File(targetFile);

        try {
            if (deleteFile.exists()) {
                deleteFile.delete();
            }
        }catch (RuntimeException e){
            e.getStackTrace();
        }
        //파일이 존재하는지 체크
    }
}
