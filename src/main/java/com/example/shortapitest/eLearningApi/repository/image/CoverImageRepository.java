package com.example.shortapitest.eLearningApi.repository.image;

import com.example.shortapitest.eLearningApi.entity.image.CoverImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoverImageRepository extends JpaRepository<CoverImage, Long> {

}
