package com.example.shortapitest.eLearningApi.repository.queryDsl;

import com.example.shortapitest.eLearningApi.entity.eLearning.content.ELearningCategory;
import com.example.shortapitest.eLearningApi.entity.eLearning.content.ELearningContent;
import com.example.shortapitest.eLearningApi.entity.eLearning.content.ELearningMenu;
import com.example.shortapitest.eLearningApi.entity.eLearning.question.ELearningQuestion;
import com.example.shortapitest.eLearningApi.entity.image.MenuImage;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.shortapitest.eLearningApi.entity.eLearning.content.QELearningContent.eLearningContent;
import static com.example.shortapitest.eLearningApi.entity.eLearning.content.QELearningCategory.eLearningCategory;
import static com.example.shortapitest.eLearningApi.entity.eLearning.content.QELearningMenu.eLearningMenu;
import static com.example.shortapitest.eLearningApi.entity.eLearning.question.QELearningQuestion.eLearningQuestion;
import static com.example.shortapitest.eLearningApi.entity.eLearning.question.QELearningChoice.eLearningChoice;
import static com.example.shortapitest.eLearningApi.entity.image.QMenuImage.menuImage;

@RequiredArgsConstructor
@Repository
public class ELQuery {

    private final JPAQueryFactory jpaQueryFactory;


    public ELearningContent findBySetting(Long settingId){

        return jpaQueryFactory
                .selectFrom(eLearningContent)
                .where(eLearningContent.eLearningSetting.id.eq(settingId))
                .fetchOne();

    }

    public List<ELearningCategory> findByContentId(Long contentId){

        return jpaQueryFactory
                .selectFrom(eLearningCategory)
                .leftJoin(eLearningCategory.eLearningMenuList,eLearningMenu)
                .fetchJoin()
                .where(eLearningCategory.eLearningContent.id.eq(contentId))
                .fetch();
    }

    public List<ELearningQuestion> findByQuestionId(Long settingId) {
        return jpaQueryFactory
                .selectFrom(eLearningQuestion)
                .leftJoin(eLearningQuestion.eLearningChoiceList, eLearningChoice)
                .fetchJoin()
                .where(eLearningQuestion.eLearningSetting.id.eq(settingId))
                .fetch();
    }

    public List<ELearningMenu> findByMenuId(Long categoryId) {
        return jpaQueryFactory
                .selectFrom(eLearningMenu)
                .leftJoin(eLearningMenu.menuImageList, menuImage)
                .fetchJoin()
                .where(eLearningMenu.eLearningCategory.id.eq(categoryId))
                .fetch();
    }

}
