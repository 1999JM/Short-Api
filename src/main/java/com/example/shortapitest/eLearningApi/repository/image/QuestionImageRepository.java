package com.example.shortapitest.eLearningApi.repository.image;

import com.example.shortapitest.eLearningApi.entity.image.QBaseImage;
import com.example.shortapitest.eLearningApi.entity.image.QuestionImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionImageRepository extends JpaRepository<QuestionImage, Long> {

    @Query(" select q from QuestionImage q where q.eLearningQuestion.id = :questionId")
    QuestionImage findByQuestionId(@Param("questionId") Long questionId);
}
