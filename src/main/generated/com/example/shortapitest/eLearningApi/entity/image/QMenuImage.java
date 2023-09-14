package com.example.shortapitest.eLearningApi.entity.image;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMenuImage is a Querydsl query type for MenuImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMenuImage extends EntityPathBase<MenuImage> {

    private static final long serialVersionUID = 830681128L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMenuImage menuImage = new QMenuImage("menuImage");

    public final QBaseImage _super = new QBaseImage(this);

    public final com.example.shortapitest.eLearningApi.entity.eLearning.content.QELearningMenu eLearningMenu;

    //inherited
    public final StringPath filename = _super.filename;

    //inherited
    public final StringPath fileOriName = _super.fileOriName;

    //inherited
    public final StringPath fileUrl = _super.fileUrl;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> menuImageSequence = createNumber("menuImageSequence", Long.class);

    public QMenuImage(String variable) {
        this(MenuImage.class, forVariable(variable), INITS);
    }

    public QMenuImage(Path<? extends MenuImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMenuImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMenuImage(PathMetadata metadata, PathInits inits) {
        this(MenuImage.class, metadata, inits);
    }

    public QMenuImage(Class<? extends MenuImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.eLearningMenu = inits.isInitialized("eLearningMenu") ? new com.example.shortapitest.eLearningApi.entity.eLearning.content.QELearningMenu(forProperty("eLearningMenu"), inits.get("eLearningMenu")) : null;
    }

}

