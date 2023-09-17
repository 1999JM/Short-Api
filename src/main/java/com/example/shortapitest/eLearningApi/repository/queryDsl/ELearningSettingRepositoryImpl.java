package com.example.shortapitest.eLearningApi.repository.queryDsl;

import com.example.shortapitest.eLearningApi.entity.eLearning.ELearningSetting;
import com.example.shortapitest.eLearningApi.entity.eLearning.QELearningSetting;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ELearningSettingRepositoryImpl implements ELearningSettingRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public PageImpl<ELearningSetting> selectELearningSetting(Pageable pageable) {
        //찾아본 결과 fetchResults() 향후 사라질 기능으로 조회 쿼리와 총 개수를 찾는 쿼리를 분리하여 작성
        QELearningSetting eLearningSetting = QELearningSetting.eLearningSetting;
        List<ELearningSetting> content = queryFactory
                // 리스트로 리턴해줘야 하는 값들 이러닝 네임, 이러닝 별칭, 이러닝, 삭제 처리 여부
                .selectFrom(eLearningSetting)
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

    // 검색 엔진을 위한 메소드 (사번과 이름별 조회)
    private BooleanExpression searchByCondition(String searchBy, String searchQuery) {
        if(StringUtils.equals("name", searchBy)){ // 회원 이름으로 검색
            return QMember.member.name.like("%" + searchQuery + "%" ) ;

        }else if(StringUtils.equals("id", searchBy)){ // 사번으로 검색
            return QMember.member.id.like("%" + searchQuery + "%" ) ;
        }

        return null ;
    }
}
