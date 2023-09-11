package com.example.shortapitest.eLearningApi.entity.eLearning;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QELearningChoice is a Querydsl query type for ELearningChoice
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QELearningChoice extends EntityPathBase<ELearningChoice> {

    private static final long serialVersionUID = -178860072L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QELearningChoice eLearningChoice = new QELearningChoice("eLearningChoice");

    public final BooleanPath answer = createBoolean("answer");

    public final StringPath choiceContent = createString("choiceContent");

    public final QELearningQuestion eLearningQuestion;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QELearningChoice(String variable) {
        this(ELearningChoice.class, forVariable(variable), INITS);
    }

    public QELearningChoice(Path<? extends ELearningChoice> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QELearningChoice(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QELearningChoice(PathMetadata metadata, PathInits inits) {
        this(ELearningChoice.class, metadata, inits);
    }

    public QELearningChoice(Class<? extends ELearningChoice> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.eLearningQuestion = inits.isInitialized("eLearningQuestion") ? new QELearningQuestion(forProperty("eLearningQuestion"), inits.get("eLearningQuestion")) : null;
    }

}

