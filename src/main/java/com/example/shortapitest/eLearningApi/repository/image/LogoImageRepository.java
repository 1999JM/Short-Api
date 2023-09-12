package com.example.shortapitest.eLearningApi.repository.image;

import com.example.shortapitest.eLearningApi.entity.image.LogoImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogoImageRepository extends JpaRepository<LogoImage, Long>, LogoImageRepositoryCustom {

}
