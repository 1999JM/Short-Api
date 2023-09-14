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
public class LogoImage extends BaseImage {

    @Id
    @Column(name = "logo_image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne(mappedBy = "logoImage", fetch = FetchType.LAZY)
    @JoinColumn(name = "e_learning_Setting_id", insertable = false, updatable = false)
    private ELearningSetting eLearningSetting;

    public static LogoImage setLogoImage(String newImageName, String oriImageName, String imageUrl){

        LogoImage saveLogoImage = LogoImage.builder()
                .filename(newImageName)
                .fileOriName(oriImageName)
                .fileUrl(imageUrl)
                .build();

        return saveLogoImage;
    }

}
