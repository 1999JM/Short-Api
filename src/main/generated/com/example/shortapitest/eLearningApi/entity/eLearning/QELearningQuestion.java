package com.example.shortapitest.eLearningApi.entity.eLearning;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QELearningQuestion is a Querydsl query type for ELearningQuestion
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QELearningQuestion extends EntityPathBase<ELearningQuestion> {

    private static final long serialVersionUID = 1214774365L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QELearningQuestion eLearningQuestion = new QELearningQuestion("eLearningQuestion");

    public final StringPath Answer = createString("Answer");

    public final QELearning eLearning;

    public final ListPath<ELearningChoice, QELearningChoice> eLearningChoice = this.<ELearningChoice, QELearningChoice>createList("eLearningChoice", ELearningChoice.class, QELearningChoice.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.example.shortapitest.eLearningApi.entity.image.QQuestionImage questionImage;

    public final StringPath questionName = createString("questionName");

    public QELearningQuestion(String variable) {
        this(ELearningQuestion.class, forVariable(variable), INITS);
    }

    public QELearningQuestion(Path<? extends ELearningQuestion> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QELearningQuestion(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QELearningQuestion(PathMetadata metadata, PathInits inits) {
        this(ELearningQuestion.class, metadata, inits);
    }

    public QELearningQuestion(Class<? extends ELearningQuestion> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.eLearning = inits.isInitialized("eLearning") ? new QELearning(forProperty("eLearning"), inits.get("eLearning")) : null;
        this.questionImage = inits.isInitialized("questionImage") ? new com.example.shortapitest.eLearningApi.entity.image.QQuestionImage(forProperty("questionImage"), inits.get("questionImage")) : null;
    }

}

