package com.example.shortapitest.eLearningApi.entity.eLearning.content;

import com.example.shortapitest.eLearningApi.entity.eLearning.ELearningSetting;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ELearningContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "e_learning_content_id")
    private Long id;

    @OneToMany(
            mappedBy = "eLearningContent",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ELearningCategory> eLearningCategory = new ArrayList<ELearningCategory>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "e_learning_Setting_id")
    private ELearningSetting eLearningSetting;

    public void setELearningCategory(ELearningCategory eLearningCategory) {
        this.eLearningCategory.add(eLearningCategory);
    }

    public static ELearningContent createELearningContent(ELearningSetting eLearningSetting) {
        ELearningContent eLearningContent = ELearningContent.builder()
                .eLearningSetting(eLearningSetting)
                .build();
        return eLearningContent;
    }

    public void setELearningCategories(List<ELearningCategory> eLearningCategories) {
        this.eLearningCategory = eLearningCategories;
    }
}
