package com.example.shortapitest.eLearningApi.dto.response;

import com.example.shortapitest.eLearningApi.dto.request.create.ELMenuCreateDto;
import com.example.shortapitest.eLearningApi.entity.eLearning.content.ELearningCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ELCategoryReturnDto {

    private Long categoryId;

    private String categoryName;

    private int categorySequence;

    private List<ELMenuReturnDto> menuList = new ArrayList<>();

    public static ELCategoryReturnDto setELCategoryReturnDto (ELearningCategory eLearningCategory) {
        ELCategoryReturnDto elCategoryReturnDto = new ELCategoryReturnDto();

        elCategoryReturnDto.setCategoryId(eLearningCategory.getId());
        elCategoryReturnDto.setCategoryName(eLearningCategory.getCategoryName());
        elCategoryReturnDto.setCategorySequence(eLearningCategory.getCategorySequence());

        return elCategoryReturnDto;
    }

    public void addMenuReturnDto(ELMenuReturnDto setELMenuReturnDto) {
        this.menuList.add(setELMenuReturnDto);
    }
}