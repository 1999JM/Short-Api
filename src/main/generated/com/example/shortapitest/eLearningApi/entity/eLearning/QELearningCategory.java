package com.example.shortapitest.eLearningApi.entity.eLearning;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QELearningCategory is a Querydsl query type for ELearningCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QELearningCategory extends EntityPathBase<ELearningCategory> {

    private static final long serialVersionUID = -1863811723L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QELearningCategory eLearningCategory = new QELearningCategory("eLearningCategory");

    public final StringPath categoryName = createString("categoryName");

    public final QELearning eLearning;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<ELearningMenu, QELearningMenu> menuName = this.<ELearningMenu, QELearningMenu>createList("menuName", ELearningMenu.class, QELearningMenu.class, PathInits.DIRECT2);

    public QELearningCategory(String variable) {
        this(ELearningCategory.class, forVariable(variable), INITS);
    }

    public QELearningCategory(Path<? extends ELearningCategory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QELearningCategory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QELearningCategory(PathMetadata metadata, PathInits inits) {
        this(ELearningCategory.class, metadata, inits);
    }

    public QELearningCategory(Class<? extends ELearningCategory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.eLearning = inits.isInitialized("eLearning") ? new QELearning(forProperty("eLearning"), inits.get("eLearning")) : null;
    }

}

