package com.example.shortapitest.eLearningApi.entity.eLearning.question;

import com.example.shortapitest.eLearningApi.dto.ELearningChoiceDto;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String choiceContent;

    @Column
    private boolean answer;

    @Column(nullable = false)
    private int choiceSequence;

    @ManyToOne(fetch = FetchType.EAGER)
    private ELearningQuestion eLearningQuestion;

    public static ELearningChoice createELearningChoice(ELearningChoiceDto choiceDto, ELearningQuestion eLearningQuestion, int count) {

        ELearningChoice eLearningChoice = ELearningChoice.builder()
                .choiceContent(choiceDto.getChoiceName())
                .eLearningQuestion(eLearningQuestion)
                .choiceSequence(count)
                .build();
        return eLearningChoice;

    }
}
