package com.example.shortapitest.eLearningApi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestELearningCategory {

    //eLearningCategory, Menu 설정

    @Schema(description = "카테고리 이름")
    @NotBlank(message = "카테고리 이름을 작성해 주세요.")
    private String categoryName;

    @Schema(description = "메뉴 이름")
    @NotBlank(message = "메뉴 이름을 작성해 주세요.")
    private List<String> menuName;

    //메뉴 이미지는 열러개 들어올 수 있습니다.

}
