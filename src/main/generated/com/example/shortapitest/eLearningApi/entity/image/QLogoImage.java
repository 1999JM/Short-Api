package com.example.shortapitest.eLearningApi.entity.image;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLogoImage is a Querydsl query type for LogoImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLogoImage extends EntityPathBase<LogoImage> {

    private static final long serialVersionUID = 797036380L;

    public static final QLogoImage logoImage = new QLogoImage("logoImage");

    public final QBaseImage _super = new QBaseImage(this);

    //inherited
    public final StringPath filename = _super.filename;

    //inherited
    public final StringPath fileOriName = _super.fileOriName;

    //inherited
    public final StringPath fileUrl = _super.fileUrl;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QLogoImage(String variable) {
        super(LogoImage.class, forVariable(variable));
    }

    public QLogoImage(Path<? extends LogoImage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLogoImage(PathMetadata metadata) {
        super(LogoImage.class, metadata);
    }

}

