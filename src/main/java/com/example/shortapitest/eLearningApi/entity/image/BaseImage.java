package com.example.shortapitest.eLearningApi.entity.image;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

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

}
