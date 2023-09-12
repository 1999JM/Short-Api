package com.example.shortapitest.eLearningApi.repository.eLearning;

import com.example.shortapitest.eLearningApi.dto.RequestELearning;
import com.example.shortapitest.eLearningApi.entity.eLearning.ELearning;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional
public class ELearningRepositoryImpl implements ELearningRepositoryCustom {


    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManager;


    @Override
    public ELearning createELearning(RequestELearning requestELearning ) {

        ELearning eLearning = ELearning.builder()
                .name(requestELearning.getELearningName())
                .alias(requestELearning.getELearningAlias())
                .wrongAnswerSkip(requestELearning.isWrongAnswerSkip())
                .displayAnswer(requestELearning.isDisplayAnswer())
                .TestPassScore(requestELearning.getTestPassScore())
                //.coverImage()
                //.logoImage()
                .build();

        entityManager.persist(eLearning);

        return eLearning;
    }
}
