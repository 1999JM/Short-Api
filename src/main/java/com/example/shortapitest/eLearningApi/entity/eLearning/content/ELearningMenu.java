package com.example.shortapitest.eLearningApi.entity.eLearning.content;

import com.example.shortapitest.eLearningApi.dto.request.create.ELMenuCreateDto;
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
            mappedBy = "eLearningMenu",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<MenuImage> menuImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "e_Learning_category_id", updatable = false)
    private ELearningCategory eLearningCategory;


    public static ELearningMenu createManu(ELMenuCreateDto eLearningMenuDto, int menuSequence, ELearningCategory eLearningCategory) {
        ELearningMenu eLearningMenu = ELearningMenu.builder()
                .menuName(eLearningMenuDto.getMenuName())
                .menuSequence(menuSequence)
                .eLearningCategory(eLearningCategory)
                .build();
        return eLearningMenu;
    }
}
