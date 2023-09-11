package com.example.shortapitest.eLearningApi.entity.image;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuestionImage is a Querydsl query type for QuestionImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuestionImage extends EntityPathBase<QuestionImage> {

    private static final long serialVersionUID = -1504464895L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuestionImage questionImage = new QQuestionImage("questionImage");

    public final QBaseImage _super = new QBaseImage(this);

    public final com.example.shortapitest.eLearningApi.entity.eLearning.QELearningQuestion eLearningQuestion;

    //inherited
    public final StringPath filename = _super.filename;

    //inherited
    public final StringPath fileOriName = _super.fileOriName;

    //inherited
    public final StringPath fileUrl = _super.fileUrl;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public QQuestionImage(String variable) {
        this(QuestionImage.class, forVariable(variable), INITS);
    }

    public QQuestionImage(Path<? extends QuestionImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQuestionImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQuestionImage(PathMetadata metadata, PathInits inits) {
        this(QuestionImage.class, metadata, inits);
    }

    public QQuestionImage(Class<? extends QuestionImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.eLearningQuestion = inits.isInitialized("eLearningQuestion") ? new com.example.shortapitest.eLearningApi.entity.eLearning.QELearningQuestion(forProperty("eLearningQuestion"), inits.get("eLearningQuestion")) : null;
    }

}

