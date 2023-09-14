package com.example.shortapitest.eLearningApi.entity.eLearning.content;

import com.example.shortapitest.eLearningApi.entity.eLearning.ELearningSetting;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ELearningContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "e_learning_content_id")
    private Long id;

    @Builder.Default
    @OneToMany(
            mappedBy = "eLearningContent",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ELearningCategory> eLearningCategory = new ArrayList<ELearningCategory>(); //수정완료.

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "e_learning_Setting_id")
    private ELearningSetting eLearningSetting;

    public void setELearningCategory(ELearningCategory eLearningCategory) {
        this.eLearningCategory.add(eLearningCategory);
    }

    public static ELearningContent createELearningContent(ELearningSetting eLearningSetting) {
        return ELearningContent.builder()
                .eLearningSetting(eLearningSetting)
                .build();
    }
}
