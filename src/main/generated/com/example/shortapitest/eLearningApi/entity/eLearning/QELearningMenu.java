package com.example.shortapitest.eLearningApi.entity.eLearning;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QELearningMenu is a Querydsl query type for ELearningMenu
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QELearningMenu extends EntityPathBase<ELearningMenu> {

    private static final long serialVersionUID = 108886L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QELearningMenu eLearningMenu = new QELearningMenu("eLearningMenu");

    public final QELearningCategory eLearningCategory;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath menuName = createString("menuName");

    public QELearningMenu(String variable) {
        this(ELearningMenu.class, forVariable(variable), INITS);
    }

    public QELearningMenu(Path<? extends ELearningMenu> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QELearningMenu(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QELearningMenu(PathMetadata metadata, PathInits inits) {
        this(ELearningMenu.class, metadata, inits);
    }

    public QELearningMenu(Class<? extends ELearningMenu> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.eLearningCategory = inits.isInitialized("eLearningCategory") ? new QELearningCategory(forProperty("eLearningCategory"), inits.get("eLearningCategory")) : null;
    }

}

