package com.example.shortapitest.eLearningApi.dto.response;

import com.example.shortapitest.eLearningApi.entity.eLearning.content.ELearningMenu;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ELMenuReturnDto {

    private Long menuId;

    private String menuName;

    private int menuSequence;

    private List<ELImageReturnDto> elImageReturnDtoList = new ArrayList<>();

    public static ELMenuReturnDto setELMenuReturnDto(ELearningMenu menuDto) {
        ELMenuReturnDto elMenuReturnDto = new ELMenuReturnDto();
        elMenuReturnDto.setMenuId(menuDto.getId());
        elMenuReturnDto.setMenuName(menuDto.getMenuName());
        elMenuReturnDto.setMenuSequence(menuDto.getMenuSequence());
        elMenuReturnDto.setElImageReturnDtoList(menuDto.getMenuImageList().stream().map(ELImageReturnDto::setELImageReturnDto).toList());
        return elMenuReturnDto;
    }

    public void addELImageReturnDto(ELImageReturnDto eLImageReturnDto) {
        this.elImageReturnDtoList.add(eLImageReturnDto);
    }
}
