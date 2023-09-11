package com.example.shortapitest.eLearningApi.entity.eLearning;

import com.example.shortapitest.eLearningApi.constant.AnswerType;
import com.example.shortapitest.eLearningApi.entity.image.QuestionImage;
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
public class ELearningQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "E_Learning_Question")
    private Long id;

    @Column(nullable = false)
    private String questionName;

    @ManyToOne(fetch = FetchType.LAZY)
    private ELearning eLearning;

    @OneToOne(
            fetch = FetchType.LAZY,
            mappedBy = "eLearningQuestion",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private QuestionImage questionImage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AnswerType answerType;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "eLearningQuestion",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ELearningChoice> eLearningChoice;

}
