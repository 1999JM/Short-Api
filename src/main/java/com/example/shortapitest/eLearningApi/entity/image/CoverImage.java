package com.example.shortapitest.eLearningApi.entity.image;

import com.example.shortapitest.eLearningApi.entity.eLearning.ELearningSetting;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CoverImage extends BaseImage {

    @Id
    @Column(name = "cover_image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public CoverImage setCoverImage(String newImageName, String oriImageName, String imageUrl){

        CoverImage saveCoverImage = CoverImage.builder()
                .filename(newImageName)
                .fileOriName(oriImageName)
                .fileUrl(imageUrl)
                .build();

        return saveCoverImage;
    }

}
