package com.example.shortapitest.eLearningApi.eLearning.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ELearning {

    @Id
    @Column(name = "e_learning_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "e_learning_name", nullable = false)
    private String name;

    @Column(name = "e_learning_alias", nullable = false)
    private String alias;

    @Column(nullable = false)
    private Boolean wrongAnswerSkip;    //오답시 다음 문제 진행 여부 기본값: false

    @Column(nullable = false)
    private Boolean displayAnswer;      // 정답 공개 여부 설정 기본값: false

    @Column(nullable = false)
    private int TestPassScore;          //eLearning 수료 기준 0~100 소숫점은 반올림 수료시 Certification

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "e_learning_menu_id")
    private ELearningImage coverImage;
}
