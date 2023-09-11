package com.example.shortapitest.urlApi.url.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUrl is a Querydsl query type for Url
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUrl extends EntityPathBase<Url> {

    private static final long serialVersionUID = -47849319L;

    public static final QUrl url = new QUrl("url");

    public final ListPath<com.example.shortapitest.urlApi.access.entity.AccessLog, com.example.shortapitest.urlApi.access.entity.QAccessLog> accessLog = this.<com.example.shortapitest.urlApi.access.entity.AccessLog, com.example.shortapitest.urlApi.access.entity.QAccessLog>createList("accessLog", com.example.shortapitest.urlApi.access.entity.AccessLog.class, com.example.shortapitest.urlApi.access.entity.QAccessLog.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final StringPath destinationUrl = createString("destinationUrl");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> lestClicked = createDateTime("lestClicked", java.time.LocalDateTime.class);

    public final StringPath shortUrl = createString("shortUrl");

    public final NumberPath<Long> totalClicks = createNumber("totalClicks", Long.class);

    public QUrl(String variable) {
        super(Url.class, forVariable(variable));
    }

    public QUrl(Path<? extends Url> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUrl(PathMetadata metadata) {
        super(Url.class, metadata);
    }

}

