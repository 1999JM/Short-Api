package com.example.shortapitest.eLearningApi.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ELChoiceDto {

    @Schema(description = "문제에 대한 보기")
    @NotBlank(message = "보기를 작성해 주세요.")
    private String choiceName;

    @Schema(description = "보기 항목 정답 여부 설정")
    @NotBlank(message = "보기 항목의 정답 여부를 설정해 주세요.")
    private boolean answerCheck;

    @Schema(description = "보기 항목에 대한 순번")
    @NotBlank(message = "보기 항목에 대한 순번을 설정해 주세요.")
    private int choiceSequence;
}
