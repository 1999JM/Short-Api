package com.example.shortapitest.eLearningApi.entity.image;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QQuestionImage is a Querydsl query type for QuestionImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuestionImage extends EntityPathBase<QuestionImage> {

    private static final long serialVersionUID = -1504464895L;

    public static final QQuestionImage questionImage = new QQuestionImage("questionImage");

    public final QBaseImage _super = new QBaseImage(this);

    //inherited
    public final StringPath filename = _super.filename;

    //inherited
    public final StringPath fileOriName = _super.fileOriName;

    //inherited
    public final StringPath fileUrl = _super.fileUrl;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QQuestionImage(String variable) {
        super(QuestionImage.class, forVariable(variable));
    }

    public QQuestionImage(Path<? extends QuestionImage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QQuestionImage(PathMetadata metadata) {
        super(QuestionImage.class, metadata);
    }

}

