package com.example.shortapitest.eLearningApi.entity.eLearning.question;

import com.example.shortapitest.eLearningApi.constant.AnswerType;
import com.example.shortapitest.eLearningApi.dto.request.create.ELearningQuestionSetDto;
import com.example.shortapitest.eLearningApi.entity.eLearning.ELearningSetting;
import com.example.shortapitest.eLearningApi.entity.image.QuestionImage;
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
@AllArgsConstructor
@NoArgsConstructor
public class ELearningQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "e_learning_question_id")
    private Long id;

    @Column(nullable = false)
    private String questionName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "e_learning_Setting_id", updatable = false)
    private ELearningSetting eLearningSetting;

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "question_image_id")
    private QuestionImage questionImage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AnswerType answerType;

    @Builder.Default
    @OneToMany(
            mappedBy = "eLearningQuestion",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ELearningChoice> eLearningChoiceList = new ArrayList<>();

    public void setQuestionImage(QuestionImage questionImage) {
        this.questionImage = questionImage;
    }


    public static ELearningQuestion createELearningQuestion(ELearningQuestionSetDto eLearningQuestionSetDto, ELearningSetting eLearningSetting) {

        String answerType = eLearningQuestionSetDto.getAnswer().toUpperCase();

        ELearningQuestion eLearningQuestion = ELearningQuestion.builder()
                .questionName(eLearningQuestionSetDto.getQuestionName())
                .answerType((answerType.equals(AnswerType.RADIO.toString()) ? AnswerType.RADIO : AnswerType.CHECKBOX))
                .eLearningSetting(eLearningSetting)
                .build();
        return eLearningQuestion;
    }

    public void addChoice(ELearningChoice eLearningChoice) {
        this.eLearningChoiceList.add(eLearningChoice);
    }
}
