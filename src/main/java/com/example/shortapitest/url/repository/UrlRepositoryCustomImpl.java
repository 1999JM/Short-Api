package com.example.shortapitest.url.repository;

import com.example.shortapitest.url.entity.QUrl;
import com.example.shortapitest.url.entity.Url;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UrlRepositoryCustomImpl implements UrlRepositoryCustom {

    private JPAQueryFactory queryFactory;


}
