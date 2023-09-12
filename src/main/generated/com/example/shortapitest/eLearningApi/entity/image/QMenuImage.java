package com.example.shortapitest.eLearningApi.entity.image;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMenuImage is a Querydsl query type for MenuImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMenuImage extends EntityPathBase<MenuImage> {

    private static final long serialVersionUID = 830681128L;

    public static final QMenuImage menuImage = new QMenuImage("menuImage");

    public final QBaseImage _super = new QBaseImage(this);

    //inherited
    public final StringPath filename = _super.filename;

    //inherited
    public final StringPath fileOriName = _super.fileOriName;

    //inherited
    public final StringPath fileUrl = _super.fileUrl;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QMenuImage(String variable) {
        super(MenuImage.class, forVariable(variable));
    }

    public QMenuImage(Path<? extends MenuImage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMenuImage(PathMetadata metadata) {
        super(MenuImage.class, metadata);
    }

}

