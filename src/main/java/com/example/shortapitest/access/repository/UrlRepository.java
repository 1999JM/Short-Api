package com.example.shortapitest.access.repository;

import com.example.shortapitest.access.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url, String> {
}
