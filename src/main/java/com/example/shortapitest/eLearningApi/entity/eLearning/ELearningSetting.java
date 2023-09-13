package com.example.shortapitest.eLearningApi.entity.eLearning;

import com.example.shortapitest.eLearningApi.dto.ELearningSettingDto;
import com.example.shortapitest.eLearningApi.entity.eLearning.content.ELearningContent;
import com.example.shortapitest.eLearningApi.entity.image.CoverImage;
import com.example.shortapitest.eLearningApi.entity.image.LogoImage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ELearningSetting extends BaseELearning {

    @Id
    @Column(name = "e_learning_Setting_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "e_learning_Setting_name", nullable = false)
    private String name;

    @Column(name = "e_learning_Setting_alias", nullable = false)
    private String alias;

    @Column(nullable = false)
    private boolean wrongAnswerSkip;    //오답시 다음 문제 진행 여부 기본값: false

    @Column(nullable = false)
    private boolean displayAnswer;      // 정답 공개 여부 설정 기본값: false

    @Column(nullable = false)
    private int TestPassScore;          //eLearning 수료 기준 0~100 소숫점은 반올림 수료시 Certification

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "cover_image_id")
    private CoverImage coverImage;      //커버 이미지 1건

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "logo_image_id")
    private LogoImage logoImage;        //로고 이미지 1건

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "e_learning_content_id")
    private ELearningContent eLearningContent;

    public void setELearningContent (ELearningContent eLearningContent){
        this.eLearningContent = eLearningContent;
    }

    public static ELearningSetting createELearningSetting(ELearningSettingDto ELearningSettingDto, LogoImage logoImage, CoverImage coverImage){

        ELearningSetting eLearningSetting = ELearningSetting.builder()
                .name(ELearningSettingDto.getELearningName())
                .alias(ELearningSettingDto.getELearningAlias())
                .TestPassScore(ELearningSettingDto.getTestPassScore())
                .displayAnswer(ELearningSettingDto.isDisplayAnswer())
                .wrongAnswerSkip(ELearningSettingDto.isWrongAnswerSkip())
                .logoImage(logoImage)
                .coverImage(coverImage)
                .build();

        return eLearningSetting;
    }
}