package com.example.shortapitest.eLearningApi.entity.image;

import com.example.shortapitest.eLearningApi.entity.eLearning.content.ELearningMenu;
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
public class MenuImage extends BaseImage {

    @Id
    @Column(name = "menu_image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public static MenuImage setMenuImage(String newImageName, String oriImageName, String imageUrl) {

        MenuImage menuImage = MenuImage.builder()
                .filename(newImageName)
                .fileOriName(oriImageName)
                .fileUrl(imageUrl)
                .build();
        return menuImage;
    }
}
