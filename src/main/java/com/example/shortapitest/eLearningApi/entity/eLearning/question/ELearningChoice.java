package com.example.shortapitest.eLearningApi.entity.eLearning.question;

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
public class ELearningChoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String choiceContent;

    @Column
    private boolean answer;

    @ManyToOne(fetch = FetchType.EAGER)
    private ELearningQuestion eLearningQuestion;
}
