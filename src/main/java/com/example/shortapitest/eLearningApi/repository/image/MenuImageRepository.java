package com.example.shortapitest.eLearningApi.repository.image;

import com.example.shortapitest.eLearningApi.entity.eLearning.content.ELearningMenu;
import com.example.shortapitest.eLearningApi.entity.image.CoverImage;
import com.example.shortapitest.eLearningApi.entity.image.MenuImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuImageRepository extends JpaRepository<MenuImage, Long> {

    @Query(" select m from MenuImage m where m.eLearningMenu.id = :menuId")
    List<MenuImage> findByMenuId(@Param("menuId") Long id);
}
