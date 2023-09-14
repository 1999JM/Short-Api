package com.example.shortapitest.eLearningApi.entity.image;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLogoImage is a Querydsl query type for LogoImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLogoImage extends EntityPathBase<LogoImage> {

    private static final long serialVersionUID = 797036380L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLogoImage logoImage = new QLogoImage("logoImage");

    public final QBaseImage _super = new QBaseImage(this);

    public final com.example.shortapitest.eLearningApi.entity.eLearning.QELearningSetting eLearningSetting;

    //inherited
    public final StringPath filename = _super.filename;

    //inherited
    public final StringPath fileOriName = _super.fileOriName;

    //inherited
    public final StringPath fileUrl = _super.fileUrl;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QLogoImage(String variable) {
        this(LogoImage.class, forVariable(variable), INITS);
    }

    public QLogoImage(Path<? extends LogoImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLogoImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLogoImage(PathMetadata metadata, PathInits inits) {
        this(LogoImage.class, metadata, inits);
    }

    public QLogoImage(Class<? extends LogoImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.eLearningSetting = inits.isInitialized("eLearningSetting") ? new com.example.shortapitest.eLearningApi.entity.eLearning.QELearningSetting(forProperty("eLearningSetting"), inits.get("eLearningSetting")) : null;
    }

}

