package com.example.shortapitest.eLearningApi.repository.queryDsl;

import com.example.shortapitest.eLearningApi.entity.eLearning.ELearningSetting;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface ELearningSettingRepositoryCustom {
    PageImpl<ELearningSetting> selectELearningSetting(Pageable pageable, String startDate, String endDate, String keyword);
}
