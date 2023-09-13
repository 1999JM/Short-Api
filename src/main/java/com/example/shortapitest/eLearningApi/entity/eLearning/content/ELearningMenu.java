package com.example.shortapitest.eLearningApi.entity.eLearning.content;

import com.example.shortapitest.eLearningApi.dto.ELearningMenuDto;
import com.example.shortapitest.eLearningApi.entity.image.MenuImage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ELearningMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "e_learning_menu_id")
    private Long id;

    @Column(nullable = false)
    private String menuName;

    @Column(nullable = false)
    private int menuSequence;


    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "menu_image_id")
    private List<MenuImage> menuImage;


    @ManyToOne(fetch = FetchType.LAZY)
    private ELearningCategory eLearningCategory;

    public void setELearningCategory(ELearningCategory eLearningCategory){
        this.eLearningCategory = eLearningCategory;
    }

    public static ELearningMenu createManu(ELearningMenuDto eLearningMenuDto, int menuSequence) {
        ELearningMenu eLearningMenu = ELearningMenu.builder()
                .menuName(eLearningMenuDto.getMenuName())
                .menuSequence(menuSequence)
                .build();
        return eLearningMenu;
    }
}
