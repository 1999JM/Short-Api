package com.example.shortapitest.eLearningApi.dto.request.update;

import com.example.shortapitest.eLearningApi.dto.request.create.ELMenuCreateDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ELCategoryUpdateDto {

    @Schema(description = "카테고리 id")
    private long id;

    @Schema(description = "카테고리 이름")
    @NotBlank(message = "카테고리 이름을 작성해 주세요.")
    private String categoryName;
    @Schema(description = "카테고리 순번")
    @NotBlank(message = "카테고리 순번을 작성해 주세요.")
    private int categorySequence;

    @Schema(description = "삭제할 메뉴 id")
    private int deleteId;

    @Schema(description = "메뉴 설정")
    @NotBlank(message = "메뉴 정보를 작성해 주세요.")
    private List<ELMenuCreateDto> menuList;

}
