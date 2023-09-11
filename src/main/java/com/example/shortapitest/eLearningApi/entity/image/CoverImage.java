package com.example.shortapitest.eLearningApi.entity.image;
import com.example.shortapitest.eLearningApi.entity.eLearning.ELearning;
import com.example.shortapitest.eLearningApi.entity.eLearning.ELearningMenu;
import jakarta.persistence.*;
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
    @OneToOne(fetch = FetchType.LAZY)
    private ELearning eLearning;

}
