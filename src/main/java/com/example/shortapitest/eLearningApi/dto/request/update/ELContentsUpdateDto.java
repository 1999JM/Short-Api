package com.example.shortapitest.eLearningApi.dto.request.update;

import com.example.shortapitest.eLearningApi.dto.request.create.ELCategoryCreateDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
public class ELContentsUpdateDto {

    @Schema(description = "ELearningContent Id")
    private Long eLearningContentId;

    @Schema(description = "카테고리 삭제할 카테고리 id")
    private List<Long> deleteCategoryId;

    @Schema(description = "카테고리 정보")
    @NotBlank(message = "카테고리 정보를 입력해 주세요.")
    private List<ELCategoryUpdateDto> elCategoryUpdateDtoList;

}
