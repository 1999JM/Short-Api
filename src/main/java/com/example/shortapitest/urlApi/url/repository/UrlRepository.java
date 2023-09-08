package com.example.shortapitest.urlApi.url.repository;

import com.example.shortapitest.urlApi.url.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long>, UrlRepositoryCustom {

}
