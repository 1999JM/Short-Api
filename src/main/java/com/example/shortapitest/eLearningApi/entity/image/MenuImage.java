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
@NoArgsConstructor
public class MenuImage extends BaseImage {

    @Id
    @Column(name = "menu_image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int menuImageSequence;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "e_learning_menu_id", updatable = false)
    private ELearningMenu eLearningMenu;

    public static MenuImage createMenuImage(String newMenuImageName, String oriMenuImageName, String menuImageLocation, ELearningMenu eLearningMenu, int i) {

        MenuImage menuImage = MenuImage.builder()
                .filename(newMenuImageName)
                .fileOriName(oriMenuImageName)
                .fileUrl(menuImageLocation)
                .eLearningMenu(eLearningMenu)
                .deleted(false)
                .menuImageSequence(i + 1)
                .build();
        return menuImage;
    }

    public void updateMenuImage(String newMenuImageName, String oriMenuImageName, int i) {
        super.updateImage(newMenuImageName, oriMenuImageName);
        this.menuImageSequence = (i+1);
    }
}
