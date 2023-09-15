package com.example.shortapitest.eLearningApi.dto.response;

import com.example.shortapitest.eLearningApi.entity.eLearning.ELearningSetting;
import lombok.*;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PageELSettingReturnDto {

    private int totalPage;  //총 페이지

    private int currentPage; //현재 페이지

    @Builder.Default
    private List<ELSettingReturnDto> responseSettings = new ArrayList<>(); //필요 정보

    public static PageELSettingReturnDto createPageELearningSetting(PageImpl<ELearningSetting> eLearningSetting) {
        PageELSettingReturnDto pageELearningSetting = new PageELSettingReturnDto();
        pageELearningSetting.setTotalPage(eLearningSetting.getTotalPages());
        pageELearningSetting.setCurrentPage(eLearningSetting.getPageable().getPageNumber());

        return pageELearningSetting;
    }

    public void addResponseSetting(ELSettingReturnDto responseSetting) {
        this.responseSettings.add(responseSetting);
    }
}
