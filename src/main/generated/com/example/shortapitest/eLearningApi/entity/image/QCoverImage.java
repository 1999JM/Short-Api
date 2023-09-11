package com.example.shortapitest.eLearningApi.entity.image;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCoverImage is a Querydsl query type for CoverImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCoverImage extends EntityPathBase<CoverImage> {

    private static final long serialVersionUID = 1935094904L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCoverImage coverImage = new QCoverImage("coverImage");

    public final QBaseImage _super = new QBaseImage(this);

    public final com.example.shortapitest.eLearningApi.entity.eLearning.QELearning eLearning;

    //inherited
    public final StringPath filename = _super.filename;

    //inherited
    public final StringPath fileOriName = _super.fileOriName;

    //inherited
    public final StringPath fileUrl = _super.fileUrl;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public QCoverImage(String variable) {
        this(CoverImage.class, forVariable(variable), INITS);
    }

    public QCoverImage(Path<? extends CoverImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCoverImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCoverImage(PathMetadata metadata, PathInits inits) {
        this(CoverImage.class, metadata, inits);
    }

    public QCoverImage(Class<? extends CoverImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.eLearning = inits.isInitialized("eLearning") ? new com.example.shortapitest.eLearningApi.entity.eLearning.QELearning(forProperty("eLearning"), inits.get("eLearning")) : null;
    }

}

