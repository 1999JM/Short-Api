package com.example.shortapitest.eLearningApi.repository.queryDsl;

import com.example.shortapitest.eLearningApi.dto.responseDto.ResponseSetting;
import com.example.shortapitest.eLearningApi.entity.eLearning.ELearningSetting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ELearningSettingRepositoryCustom {
    Page<ResponseSetting> selectELearningSetting(Pageable pageable);
}
