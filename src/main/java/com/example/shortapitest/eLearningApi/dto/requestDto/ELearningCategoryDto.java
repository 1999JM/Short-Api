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
public class ELearningCategoryDto {

    @Schema(description = "카테고리 이름")
    @NotBlank(message = "카테고리 이름을 작성해 주세요.")
    private String CategoryName;

    @Schema(description = "메뉴 설정")
    @NotBlank(message = "메뉴 정보를 작성해 주세요.")
    private List<ELearningMenuDto> menu;

}
