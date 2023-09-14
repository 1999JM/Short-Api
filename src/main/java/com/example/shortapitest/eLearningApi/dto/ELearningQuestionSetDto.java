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

    private boolean questionImage;  //해당 문제에 이미지가 존재하는지 판별하는 판별기

    private List<ELearningChoiceDto> choiceDtoList;
}
