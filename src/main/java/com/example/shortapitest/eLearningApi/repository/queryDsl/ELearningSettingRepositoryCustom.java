package com.example.shortapitest.eLearningApi.repository.queryDsl;

import com.example.shortapitest.eLearningApi.entity.eLearning.ELearningSetting;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public interface ELearningSettingRepositoryCustom {
    PageImpl<ELearningSetting> selectELearningSetting(Pageable pageable);
}
