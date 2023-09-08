package com.example.shortapitest.eLearningApi.entity.image;
import com.example.shortapitest.eLearningApi.entity.eLearning.ELearningMenu;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CoverImage extends BaseImage {

    @ManyToOne(fetch = FetchType.LAZY)
    private ELearningMenu eLearningMenu;    //메뉴에 대한 이미지 설정

}
