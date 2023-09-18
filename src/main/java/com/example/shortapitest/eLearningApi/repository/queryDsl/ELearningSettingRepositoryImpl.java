package com.example.shortapitest.eLearningApi.repository.queryDsl;

import com.example.shortapitest.eLearningApi.entity.eLearning.ELearningSetting;
import com.example.shortapitest.eLearningApi.entity.eLearning.QELearningSetting;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTimeTemplate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ELearningSettingRepositoryImpl implements ELearningSettingRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public PageImpl<ELearningSetting> selectELearningSetting(Pageable pageable, String startDate, String endDate,String keyword) {
        //찾아본 결과 fetchResults() 향후 사라질 기능으로 조회 쿼리와 총 개수를 찾는 쿼리를 분리하여 작성
        QELearningSetting eLearningSetting = QELearningSetting.eLearningSetting;
        List<ELearningSetting> content = queryFactory
                // 리스트로 리턴해줘야 하는 값들 이러닝 네임, 이러닝 별칭, 이러닝, 삭제 처리 여부
                .selectFrom(eLearningSetting)
                .where(searchByCondition(startDate, endDate, keyword))
                .orderBy(eLearningSetting.createDate.desc())
                .offset(pageable.getOffset())   //시작 위치 설정
                .limit(pageable.getPageSize())  //한페이지에 보여지는 행의 개수
                .fetch();

        Long totalCount = queryFactory
                .select(eLearningSetting.count())
                .from(eLearningSetting)
                .fetchOne();

        return new PageImpl<>(content, pageable, totalCount);
    }

    // BooleanBuilder
    // 검색 조건은 여기에 추가해주세요.
    private BooleanBuilder searchByCondition(String startDate, String endDate, String keyword) {
        BooleanBuilder builder = new BooleanBuilder();

        return builder
                .and(withCreateDays(startDate, endDate))
                .and(nameLike(keyword));
    }

    //생성 날짜 검색
    private BooleanExpression withCreateDays(final String startDate, String endDate) {

        //DateTimeTemplate dateTime = Expressions.dateTimeTemplate(LocalDateTime.class, "DATE_FORMAT({0}, {1})", QELearningSetting.eLearningSetting.createDate , "YYYY-MM-DD HH24:MI:SS");

        if (startDate != null && endDate != null) {
            StringExpression formattedDate = Expressions.stringTemplate("FUNCTION('DATE_FORMAT', {0}, '%Y-%m-%d')", QELearningSetting.eLearningSetting.createDate);
            return formattedDate.between(startDate, endDate);
        }else {
            return null;
        }
    }

    //ELearning Name으로 검색
    private BooleanExpression nameLike(final String keyword) {
        return keyword.equals("") ? null : QELearningSetting.eLearningSetting.name.like("%" + keyword + "%");
    }
}
