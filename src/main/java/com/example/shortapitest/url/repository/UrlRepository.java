package com.example.shortapitest.url.repository;

import com.example.shortapitest.url.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

    @Query("select u from Url u where u.destination_url = :url ")
    Url findByUrl(@Param("url") String url);
}
