package com.example.shortapitest.eLearningApi.entity.eLearning;

import com.example.shortapitest.eLearningApi.entity.image.QuestionImage;
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
public class ELearningQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "E_Learning_Question")
    private Long id;

    @Column(nullable = false)
    private String questionName;

    @ManyToOne(fetch = FetchType.LAZY)
    private ELearning eLearning;

    @OneToOne(
            fetch = FetchType.LAZY,
            mappedBy = "eLearningQuestion",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private QuestionImage questionImage;

    @Column(nullable = false)
    private String Answer;
    //복수 정답 또는 단일 정답 선택 항목 정답인 1,2,3,4,5 이런 형식으로 복수 정답 번호를 받아서 처리
    //ex) 1로 스트링이 들어오면 첫번째 항목이 정답
    //ex) 1,2로 데이터가 들어온다면 1번 2번이 첫번째, 두번째 항목이 정답

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "eLearningQuestion",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ELearningChoice> eLearningChoice;

}
