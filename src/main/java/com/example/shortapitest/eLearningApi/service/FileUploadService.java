package com.example.shortapitest.eLearningApi.service;

import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.util.UUID;

@Service
public class FileUploadService {

    public String uploadFile(String upLoadPath, String originalFileName, byte[] fileData) throws Exception{

        UUID uuid = UUID.randomUUID();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String savedFileName = uuid.toString()+extension;

        String fileUploadFullUrl= upLoadPath+"/"+savedFileName;

        FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);

        fos.write(fileData);

        fos.close();

        return savedFileName;

    }
}
