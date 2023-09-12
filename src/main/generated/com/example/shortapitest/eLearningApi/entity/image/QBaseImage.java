package com.example.shortapitest.eLearningApi.entity.image;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseImage is a Querydsl query type for BaseImage
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBaseImage extends EntityPathBase<BaseImage> {

    private static final long serialVersionUID = 541021686L;

    public static final QBaseImage baseImage = new QBaseImage("baseImage");

    public final StringPath filename = createString("filename");

    public final StringPath fileOriName = createString("fileOriName");

    public final StringPath fileUrl = createString("fileUrl");

    public QBaseImage(String variable) {
        super(BaseImage.class, forVariable(variable));
    }

    public QBaseImage(Path<? extends BaseImage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseImage(PathMetadata metadata) {
        super(BaseImage.class, metadata);
    }

}

