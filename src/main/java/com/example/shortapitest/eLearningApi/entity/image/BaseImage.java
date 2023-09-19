package com.example.shortapitest.eLearningApi.entity.image;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@MappedSuperclass
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BaseImage {
    @Column(nullable = false)
    private String filename;    //변경시킨 이미지 이름

    @Column(nullable = false)
    private String fileOriName; //원본 이름

    @Column(nullable = false)
    private String fileUrl;    //저장소 경로

    @Column(nullable = false)
    private boolean deleted;            //Delete 삭제 여부

    protected void updateImage(String newImageName, String originalFilename) {
        this.filename = newImageName;
        this.fileOriName = originalFilename;
    }

}
