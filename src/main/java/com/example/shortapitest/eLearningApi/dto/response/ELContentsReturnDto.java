package com.example.shortapitest.eLearningApi.dto.response;

import com.example.shortapitest.eLearningApi.entity.eLearning.content.ELearningCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ELContentsReturnDto {

    private Long eLSettingId;

    private List<ELCategoryReturnDto> elCategoryReturnDtoList = new ArrayList<>();

    public void addELCategoryReturnDtoList (ELCategoryReturnDto elCategoryReturnDto, Long eLSettingId,List<ELCategoryReturnDto> elCategoryReturnDtoList) {
        //this.elCategoryReturnDtoList.add(elCategoryReturnDto);
        this.eLSettingId = eLSettingId;
        this.elCategoryReturnDtoList = elCategoryReturnDtoList;
    }
}
