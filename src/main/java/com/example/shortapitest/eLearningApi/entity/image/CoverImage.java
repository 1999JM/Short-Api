package com.example.shortapitest.eLearningApi.entity.image;

import com.example.shortapitest.eLearningApi.entity.eLearning.ELearningSetting;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
public class CoverImage extends BaseImage {

    @Id
    @Column(name = "cover_image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne(mappedBy = "coverImage", fetch = FetchType.LAZY)
    @JoinColumn(name = "e_learning_Setting_id", insertable = false, updatable = false)
    private ELearningSetting eLearningSetting;

    public CoverImage setCoverImage(String newImageName, String oriImageName, String imageUrl){

        CoverImage saveCoverImage = CoverImage.builder()
                .filename(newImageName)
                .fileOriName(oriImageName)
                .fileUrl(imageUrl)
                .build();

        return saveCoverImage;
    }

}
