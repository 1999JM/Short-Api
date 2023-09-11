package com.example.shortapitest.eLearningApi.entity.image;
import com.example.shortapitest.eLearningApi.entity.eLearning.ELearning;
import com.example.shortapitest.eLearningApi.entity.eLearning.ELearningMenu;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LogoImage extends BaseImage {

    @OneToOne(fetch = FetchType.LAZY)
    private ELearning eLearning;

}
