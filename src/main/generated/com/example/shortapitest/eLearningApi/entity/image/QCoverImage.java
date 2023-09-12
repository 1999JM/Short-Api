package com.example.shortapitest.eLearningApi.entity.image;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCoverImage is a Querydsl query type for CoverImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCoverImage extends EntityPathBase<CoverImage> {

    private static final long serialVersionUID = 1935094904L;

    public static final QCoverImage coverImage = new QCoverImage("coverImage");

    public final QBaseImage _super = new QBaseImage(this);

    //inherited
    public final StringPath filename = _super.filename;

    //inherited
    public final StringPath fileOriName = _super.fileOriName;

    //inherited
    public final StringPath fileUrl = _super.fileUrl;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QCoverImage(String variable) {
        super(CoverImage.class, forVariable(variable));
    }

    public QCoverImage(Path<? extends CoverImage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCoverImage(PathMetadata metadata) {
        super(CoverImage.class, metadata);
    }

}

