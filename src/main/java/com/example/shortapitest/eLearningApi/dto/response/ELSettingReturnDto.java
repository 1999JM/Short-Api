package com.example.shortapitest.eLearningApi.dto.response;

import com.example.shortapitest.eLearningApi.entity.eLearning.ELearningSetting;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ELSettingReturnDto {

    private Long id;
    private String eLearningName;
    private String eLearningAlias;
    private boolean deleted;

    public static Page<ELSettingReturnDto> createResponseSetting(PageImpl<ELearningSetting> eLearningSettingList) {

        return eLearningSettingList.map(eLearningSetting -> ELSettingReturnDto.builder()
                .id(eLearningSetting.getId())
                .eLearningName(eLearningSetting.getName())
                .eLearningAlias(eLearningSetting.getAlias())
                .deleted(eLearningSetting.isDeleted())
                .build());
    }
}












