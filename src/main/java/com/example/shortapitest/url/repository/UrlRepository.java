package com.example.shortapitest.url.repository;

import com.example.shortapitest.url.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long>, UrlRepositoryCustom {

}
