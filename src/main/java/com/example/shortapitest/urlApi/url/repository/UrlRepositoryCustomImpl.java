package com.example.shortapitest.urlApi.url.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UrlRepositoryCustomImpl implements UrlRepositoryCustom {

    private JPAQueryFactory queryFactory;


}
