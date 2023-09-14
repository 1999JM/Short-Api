package com.example.shortapitest.eLearningApi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ELearningQuestionSetDto {

    @Schema(description = "문제 내용")
    @NotBlank(message = "문제 내용을 작성해 주세요.")
    private String questionName;

    @Schema(description = "단일 정답 또는 복수 정답 설정")
    @NotBlank(message = "단일 정답 또는 복수 정답 설정을 해주세요.")
    private String answer;

    @Schema(description = "해당 문제에 대한 이미지 존재 여부")
    @NotBlank(message = "이미지가 존재하는지 설정해 주세요.")
    private boolean questionImage;  //해당 문제에 이미지가 존재하는지 판별하는 판별기

    private List<ELearningChoiceDto> choiceDtoList;
}
