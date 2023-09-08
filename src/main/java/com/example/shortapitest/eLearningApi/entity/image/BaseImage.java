package com.example.shortapitest.eLearningApi.entity.image;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseImage {

    @Id
    @Column(name = "image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String filename;    //변경시킨 이미지 이름

    @Column(nullable = false)
    private String fileOriName; //원본 이름

    @Column(nullable = false)
    private String fileUrl;    //저장소 경로

}
