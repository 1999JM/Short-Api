package com.example.shortapitest.eLearningApi.dto.requestDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ELearningMenuDto {

    @Schema(description = "메뉴 이름")
    @NotBlank(message = "메뉴 이름을 작성해 주세요.")
    private String menuName;

    @Schema(description = "해당 메뉴에 대한 이미지 개수")
    @NotBlank(message = "이미지 개수를 입력해 주세요.")
    private int menuImageCount;
}
