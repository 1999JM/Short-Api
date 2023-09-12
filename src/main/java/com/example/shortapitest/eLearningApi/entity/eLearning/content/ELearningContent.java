package com.example.shortapitest.eLearningApi.entity.eLearning.content;

import com.example.shortapitest.eLearningApi.entity.eLearning.ELearningSetting;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ELearningContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(
            mappedBy = "eLearningContent",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ELearningCategory> eLearningCategory;

    @OneToOne(
            fetch = FetchType.LAZY
    )
    private ELearningSetting eLearningSetting;

    public void setELearningCategory(List<ELearningCategory> eLearningCategories) {
        this.eLearningCategory.add((ELearningCategory) eLearningCategories);
    }
}
