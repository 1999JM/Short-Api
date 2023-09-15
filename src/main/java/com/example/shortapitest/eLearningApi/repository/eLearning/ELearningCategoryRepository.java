package com.example.shortapitest.eLearningApi.repository.eLearning;

import com.example.shortapitest.eLearningApi.entity.eLearning.content.ELearningCategory;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ELearningCategoryRepository extends JpaRepository<ELearningCategory, Long> {

    @Query(" select c from ELearningCategory c where c.id = :id and c.categorySequence = :categorySequence ")
    ELearningCategory findByCategory(@Param("id") Long id, @Param("categorySequence") int categorySequence);
}
