package com.example.shortapitest.eLearningApi.dto.response;

import com.example.shortapitest.eLearningApi.entity.eLearning.question.ELearningChoice;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ELChoiceReturnDto {

    private Long choiceId;

    private String choiceName;

    private boolean answerCheck;

    private int choiceSequence;

    public static ELChoiceReturnDto setELChoiceReturnDto(ELearningChoice eLearningChoice) {

        ELChoiceReturnDto elChoiceReturnDto = new ELChoiceReturnDto();

        elChoiceReturnDto.setChoiceId(eLearningChoice.getId());
        elChoiceReturnDto.setChoiceName(eLearningChoice.getChoiceContent());
        elChoiceReturnDto.setAnswerCheck(eLearningChoice.isAnswer());
        elChoiceReturnDto.setChoiceSequence(eLearningChoice.getChoiceSequence());

        return elChoiceReturnDto;
    }
}
