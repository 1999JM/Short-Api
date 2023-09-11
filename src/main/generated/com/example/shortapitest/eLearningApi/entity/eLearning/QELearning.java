package com.example.shortapitest.eLearningApi.entity.eLearning;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QELearning is a Querydsl query type for ELearning
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QELearning extends EntityPathBase<ELearning> {

    private static final long serialVersionUID = 1880925015L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QELearning eLearning = new QELearning("eLearning");

    public final StringPath alias = createString("alias");

    public final com.example.shortapitest.eLearningApi.entity.image.QCoverImage coverImage;

    public final BooleanPath displayAnswer = createBoolean("displayAnswer");

    public final ListPath<ELearningCategory, QELearningCategory> eLearningCategory = this.<ELearningCategory, QELearningCategory>createList("eLearningCategory", ELearningCategory.class, QELearningCategory.class, PathInits.DIRECT2);

    public final ListPath<ELearningQuestion, QELearningQuestion> eLearningQuestion = this.<ELearningQuestion, QELearningQuestion>createList("eLearningQuestion", ELearningQuestion.class, QELearningQuestion.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.example.shortapitest.eLearningApi.entity.image.QLogoImage logoImage;

    public final StringPath name = createString("name");

    public final NumberPath<Integer> TestPassScore = createNumber("TestPassScore", Integer.class);

    public final BooleanPath wrongAnswerSkip = createBoolean("wrongAnswerSkip");

    public QELearning(String variable) {
        this(ELearning.class, forVariable(variable), INITS);
    }

    public QELearning(Path<? extends ELearning> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QELearning(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QELearning(PathMetadata metadata, PathInits inits) {
        this(ELearning.class, metadata, inits);
    }

    public QELearning(Class<? extends ELearning> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.coverImage = inits.isInitialized("coverImage") ? new com.example.shortapitest.eLearningApi.entity.image.QCoverImage(forProperty("coverImage"), inits.get("coverImage")) : null;
        this.logoImage = inits.isInitialized("logoImage") ? new com.example.shortapitest.eLearningApi.entity.image.QLogoImage(forProperty("logoImage"), inits.get("logoImage")) : null;
    }

}

