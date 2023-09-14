package com.example.shortapitest.eLearningApi.dto.requestDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ELearningQuestionDto {

    @Schema(description = "ELearning Id")
    @NotBlank(message = "ELearning Id 값을 입력해 주세요.")
    private Long eLearningId;
    private List<ELearningQuestionSetDto> eLearningQuestionSetDtos;
}
