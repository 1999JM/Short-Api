package com.example.shortapitest.eLearningApi.dto.response;

import com.example.shortapitest.eLearningApi.entity.eLearning.ELearningSetting;
import lombok.*;

@Getter
@Setter
public class ELSettingReturnDto {

    private Long id;
    private String eLearningName;
    private String eLearningAlias;
    private boolean deleted;

    public static ELSettingReturnDto createResponseSetting(ELearningSetting dto) {
        ELSettingReturnDto responseSetting = new ELSettingReturnDto();
        responseSetting.setId(dto.getId());
        responseSetting.setELearningName(dto.getName());
        responseSetting.setELearningAlias(dto.getAlias());
        responseSetting.setDeleted(dto.isDeleted());
        return responseSetting;
    }
}












