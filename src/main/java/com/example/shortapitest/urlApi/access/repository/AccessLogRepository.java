package com.example.shortapitest.urlApi.access.repository;

import com.example.shortapitest.urlApi.access.entity.AccessLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessLogRepository extends JpaRepository<AccessLog, Long> {

}
