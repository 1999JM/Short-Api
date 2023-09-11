package com.example.shortapitest.urlApi.access.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAccessLog is a Querydsl query type for AccessLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAccessLog extends EntityPathBase<AccessLog> {

    private static final long serialVersionUID = -1450288919L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAccessLog accessLog = new QAccessLog("accessLog");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath ip = createString("ip");

    public final StringPath referrerUrl = createString("referrerUrl");

    public final com.example.shortapitest.urlApi.url.entity.QUrl url;

    public final StringPath userAgent = createString("userAgent");

    public QAccessLog(String variable) {
        this(AccessLog.class, forVariable(variable), INITS);
    }

    public QAccessLog(Path<? extends AccessLog> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAccessLog(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAccessLog(PathMetadata metadata, PathInits inits) {
        this(AccessLog.class, metadata, inits);
    }

    public QAccessLog(Class<? extends AccessLog> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.url = inits.isInitialized("url") ? new com.example.shortapitest.urlApi.url.entity.QUrl(forProperty("url")) : null;
    }

}

