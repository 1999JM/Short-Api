package com.example.shortapitest.eLearningApi.dto.response;

import com.example.shortapitest.eLearningApi.entity.eLearning.content.ELearningCategory;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
        elCategoryReturnDto.setMenuList(
                eLearningCategory.getELearningMenuList().stream().map(ELMenuReturnDto::setELMenuReturnDto).toList()
        );
        return elCategoryReturnDto;
    }

    public void addMenuReturnDto(ELMenuReturnDto setELMenuReturnDto) {
        this.menuList.add(setELMenuReturnDto);
    }
}
