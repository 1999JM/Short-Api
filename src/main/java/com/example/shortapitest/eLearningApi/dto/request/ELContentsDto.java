package com.example.shortapitest.eLearningApi.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ELContentsDto {

    @Schema(description = "ELearning Id")
    @NotBlank(message = "ELearning Id 값을 입력해 주세요.")
    private Long eLSettingId;

    @Schema(description = "수정 요청시 삭제할 카테고리 id")
    private List<Long> deleteCategoryId;

    @Schema(description = "카테고리 정보")
    @NotBlank(message = "카테고리 정보를 입력해 주세요.")
    private List<ELCategoryDto> eLCategoryDtoList;

}
