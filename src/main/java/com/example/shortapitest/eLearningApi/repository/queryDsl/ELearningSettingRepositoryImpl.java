package com.example.shortapitest.eLearningApi.repository.queryDsl;

import com.example.shortapitest.eLearningApi.entity.eLearning.ELearningSetting;
import com.example.shortapitest.eLearningApi.entity.eLearning.QELearningSetting;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ELearningSettingRepositoryImpl implements ELearningSettingRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ELearningSetting> selectELearningSetting(int page, int rows, Pageable pageable) {
        QueryResults<ELearningSetting> results = queryFactory
                .selectFrom(QELearningSetting.eLearningSetting)
                .orderBy(QELearningSetting.eLearningSetting.createDate.desc())
                .offset(pageable.getOffset())
                .limit((long) rows)
                .fetchResults();
        return null;
    }

}
