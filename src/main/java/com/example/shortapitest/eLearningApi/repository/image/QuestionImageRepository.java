package com.example.shortapitest.eLearningApi.repository.image;

import com.example.shortapitest.eLearningApi.entity.image.QBaseImage;
import com.example.shortapitest.eLearningApi.entity.image.QuestionImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionImageRepository extends JpaRepository<QuestionImage, Long> {

}
