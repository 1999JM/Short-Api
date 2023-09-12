package com.example.shortapitest.eLearningApi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class RequestELearningQuestion {

    //eLearningCategory, Menu 설정

    @Schema(description = "문제 제목")
    @NotBlank(message = "문제 제목을 작성해 주세요.")
    private String questionName;

    @Schema(description = "문제에 대한 정답 항목 설정")
    @NotNull(message = "정답 항목을 선택해 주세요.")
    private List<Boolean> answer;

    @Schema(description = "문제에 대한 항목")
    @NotBlank(message = "문제에 대한 보기를 작성해 주세요.")
    private List<String> questionChoice;
}
