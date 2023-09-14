package com.example.shortapitest.eLearningApi.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ELearningQuestionDto {

    private List<ELearningQuestionSetDto> eLearningQuestionSetDtos;
}
