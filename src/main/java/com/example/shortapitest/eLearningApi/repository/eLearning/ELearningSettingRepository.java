package com.example.shortapitest.eLearningApi.repository.eLearning;

import com.example.shortapitest.eLearningApi.entity.eLearning.ELearningSetting;
import com.example.shortapitest.eLearningApi.repository.queryDsl.ELearningSettingRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ELearningSettingRepository extends JpaRepository<ELearningSetting, Long>, ELearningSettingRepositoryCustom {
}
