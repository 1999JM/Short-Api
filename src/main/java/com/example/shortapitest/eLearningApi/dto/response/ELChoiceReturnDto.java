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

    public static ELChoiceReturnDto setELChoiceReturnDto(ELearningChoice choiceDto) {

        ELChoiceReturnDto elChoiceReturnDto = new ELChoiceReturnDto();

        elChoiceReturnDto.setChoiceId(choiceDto.getId());
        elChoiceReturnDto.setChoiceName(choiceDto.getChoiceContent());
        elChoiceReturnDto.setAnswerCheck(choiceDto.isAnswer());
        elChoiceReturnDto.setChoiceSequence(choiceDto.getChoiceSequence());

        return elChoiceReturnDto;
    }
}
