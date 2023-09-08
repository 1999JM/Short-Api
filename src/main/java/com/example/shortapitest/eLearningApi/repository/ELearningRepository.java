package com.example.shortapitest.eLearningApi.repository;

import com.example.shortapitest.eLearningApi.entity.eLearning.ELearning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ELearningRepository extends JpaRepository<ELearning, Long> {
}
