package com.example.shortapitest.eLearningApi.dto.request.create;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ELChoiceCreateDto {

    @Schema(description = "문제에 대한 보기")
    @NotBlank(message = "보기를 작성해 주세요.")
    private String choiceName;

    @Schema(description = "보기 항목 정답 여부 설정")
    @NotBlank(message = "보기 항목의 정답 여부를 설정해 주세요.")
    private boolean answerCheck;
}
