package com.example.shortapitest.eLearningApi.entity;

import com.example.shortapitest.eLearningApi.discriminator.ImageDiscriminator;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "eLearning_image")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ELearningImage {

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

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ImageDiscriminator imageDiscriminator;  //이미지 정보 판별기

}
