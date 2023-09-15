package com.example.shortapitest.eLearningApi.utill;

import com.example.shortapitest.eLearningApi.dto.request.create.ImageUploadDto;

import java.io.File;
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

    public static void deleteFile(String path, String fileName) throws Exception{

        //삭제할 파일 경로 및 파일 이름
        String targetFile = path + fileName;

        File deleteFile = new File(targetFile);

        //파일이 존재하는지 체크
        if (deleteFile.exists()) {
            deleteFile.delete();
        }
    }
}
