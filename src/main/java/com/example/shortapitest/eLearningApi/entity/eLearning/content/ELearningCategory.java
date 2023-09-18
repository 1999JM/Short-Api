package com.example.shortapitest.eLearningApi.entity.eLearning.content;

import com.example.shortapitest.eLearningApi.dto.request.create.ELCategoryCreateDto;
import com.example.shortapitest.eLearningApi.dto.request.update.ELCategoryUpdateDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ELearningCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "e_Learning_category_id")
    private Long id;

    @Column(nullable = false)
    private String categoryName;

    @Column(nullable = false)
    private int categorySequence;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "e_learning_content_id", updatable = false)
    private ELearningContent eLearningContent;

    @Builder.Default
    @OneToMany(
            mappedBy = "eLearningCategory",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ELearningMenu> eLearningMenuList = new ArrayList<>();  //수정완료.

    public void addELearningMenu(ELearningMenu eLearningMenu){
        this.eLearningMenuList.add(eLearningMenu);
    }

    public static ELearningCategory createCategory(ELCategoryCreateDto eLearningCategoryDto, ELearningContent eLearningContent) {

        ELearningCategory eLearningCategory = ELearningCategory.builder()
                .categoryName(eLearningCategoryDto.getCategoryName())
                .categorySequence(eLearningCategoryDto.getCategorySequence())
                .eLearningContent(eLearningContent)
                .build();

        return eLearningCategory;
    }

    public static ELearningCategory updateDtoCategory(ELCategoryUpdateDto elCategoryUpdateDto, ELearningContent eLearningContent) {

        ELearningCategory eLearningCategory = ELearningCategory.builder()
                .categoryName(elCategoryUpdateDto.getCategoryName())
                .categorySequence(elCategoryUpdateDto.getCategorySequence())
                .eLearningContent(eLearningContent)
                .build();

        return eLearningCategory;
    }

    public void setELearningCategory(ELCategoryUpdateDto updateCategoryDto) {
        this.categoryName = updateCategoryDto.getCategoryName();
        this.categorySequence = updateCategoryDto.getCategorySequence();
    }
}
