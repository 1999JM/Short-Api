package com.example.shortapitest.eLearningApi.repository.queryDsl;

import com.example.shortapitest.eLearningApi.dto.responseDto.ResponseSetting;
import com.example.shortapitest.eLearningApi.entity.eLearning.ELearningSetting;
import com.example.shortapitest.eLearningApi.entity.eLearning.QELearningSetting;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.expression.spel.ast.Projection;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ELearningSettingRepositoryImpl implements ELearningSettingRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ResponseSetting> selectELearningSetting(Pageable pageable) {
        //찾아본 결과 fetchResults() 향후 사라질 기능으로 조회 쿼리와 총 개수를 찾는 쿼리를 분리하여 작성
        QELearningSetting eLearningSetting = QELearningSetting.eLearningSetting;
        List<ResponseSetting> content = queryFactory
                // 리스트로 리턴해줘야 하는 값들 이러닝 네임, 이러닝 별칭, 이러닝, 삭제 처리 여부
                .select(new ResponseSetting(
                        eLearningSetting.id.as("")
                        QELearningSetting.eLearningSetting.name,
                        QELearningSetting.eLearningSetting.alias,
                        QELearningSetting.eLearningSetting.deleted))
                .from(QELearningSetting.eLearningSetting)
                .orderBy(QELearningSetting.eLearningSetting.createDate.desc())
                .offset(pageable.getOffset())   //시작 위치 설정
                .limit(pageable.getPageSize())  //한페이지에 보여지는 행의 개수
                .fetch();

        Long totalCount = queryFactory
                .select(Wildcard.count)
                .from(QELearningSetting.eLearningSetting)
                .fetchOne();

        return PageableExecutionUtils.getPage(content, pageable, totalCount);
    }

}
