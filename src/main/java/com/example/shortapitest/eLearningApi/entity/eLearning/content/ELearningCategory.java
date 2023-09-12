package com.example.shortapitest.eLearningApi.entity.eLearning.content;

import com.example.shortapitest.eLearningApi.dto.ELearningCategoryDto;
import com.example.shortapitest.eLearningApi.entity.eLearning.content.ELearningMenu;
import jakarta.persistence.*;
import lombok.*;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categorySequence;

    @ManyToOne(fetch = FetchType.LAZY)
    private ELearningContent eLearningContent;

    @OneToMany(
            mappedBy = "eLearningCategory",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ELearningMenu> eLearningMenu;

    public void setELearningName(ELearningMenu eLearningMenu){
        this.eLearningMenu.add(eLearningMenu);
    }

    public static ELearningCategory createCategory(ELearningCategoryDto eLearningCategoryDto, ELearningContent eLearningContent) {

        ELearningCategory eLearningCategory = ELearningCategory.builder()
                .categoryName(eLearningCategoryDto.getCategoryName())
                .eLearningContent(eLearningContent)
                .build();

        return eLearningCategory;
    }
}
