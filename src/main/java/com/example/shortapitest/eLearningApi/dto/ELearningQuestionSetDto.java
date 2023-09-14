package com.example.shortapitest.eLearningApi.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ELearningQuestionSetDto {

    private String questionName;

    private String answer;

    private List<ELearningChoiceDto> choiceDtoList;
}
