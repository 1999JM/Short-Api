package com.example.shortapitest.eLearningApi.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ELSettingDto {

    @Schema(description = "이러닝 id 수정 요청시 값이 존재합니다")
    private Long eLSettingId;

    @Schema(description = "이러닝 이름")
    @NotBlank(message = "이름을 작성해 주세요.")
    private String eLearningName;

    @Schema(description = "이러닝 별칭")
    @NotBlank(message = "별칭을 작성해 주세요.")
    private String eLearningAlias;

    @Schema(description = "오답시 다음 문제 진행 여부")
    private boolean wrongAnswerSkip;

    @Schema(description = "오답시 문제 정답 공개 여부")
    private boolean displayAnswer;

    @Schema(description = "수료 판별 기준 점수")
    @NotNull(message = "수료 기준을 설정해 주세요.")
    private int testPassScore;

}
