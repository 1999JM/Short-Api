package com.example.shortapitest.eLearningApi.entity.eLearning.question;

import com.example.shortapitest.eLearningApi.dto.requestDto.ELearningChoiceDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ELearningChoice {

    @Id
    @Column(name = "e_learning_Choice_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String choiceContent;

    @Column
    private boolean answer;

    @Column(nullable = false)
    private int choiceSequence;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "e_learning_question_id")
    private ELearningQuestion eLearningQuestion;

    public static ELearningChoice createELearningChoice(ELearningChoiceDto choiceDto, ELearningQuestion eLearningQuestion, int count) {

        ELearningChoice eLearningChoice = ELearningChoice.builder()
                .choiceContent(choiceDto.getChoiceName())
                .eLearningQuestion(eLearningQuestion)
                .answer(choiceDto.isAnswerCheck())
                .choiceSequence(count)
                .build();
        return eLearningChoice;

    }
}
