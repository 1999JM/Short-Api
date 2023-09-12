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
public class LogoImage extends BaseImage {
    @Id
    @Column(name = "logo_image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public LogoImage setLogoImage(String newImageName, String oriImageName, String imageUrl){

        LogoImage saveLogoImage = LogoImage.builder()
                .filename(newImageName)
                .fileOriName(oriImageName)
                .fileUrl(imageUrl)
                .build();

        return saveLogoImage;
    }

}
