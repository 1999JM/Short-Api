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
    private Long eLSettingId;

    @Schema(description = "ELearning Contents Id")
    private Long eLContentsId;

    @Schema(description = "수정 요청시 삭제할 카테고리 id")
    private List<Long> deleteCategoryId;

    @Schema(description = "수정 요청시 삭제할 메뉴 id")
    private List<Long> deleteMenuId;

    @Schema(description = "수정 요청시 삭제할 이미지 id")
    private List<Long> deleteMenuImageId;

    @Schema(description = "카테고리 정보")
    private List<ELCategoryDto> eLCategoryDtoList;

}
