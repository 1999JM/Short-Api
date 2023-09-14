package com.example.shortapitest.eLearningApi.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ELearningChoiceDto {

    private String choiceName;

    private boolean answerCheck;
}
