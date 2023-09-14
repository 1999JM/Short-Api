package com.example.shortapitest.eLearningApi.repository.queryDsl;

import com.example.shortapitest.eLearningApi.entity.eLearning.ELearningSetting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ELearningSettingRepositoryCustom {
    Page<ELearningSetting> selectELearningSetting(int page, int rows, Pageable pageable);
}
