package com.example.shortapitest.eLearningApi.entity.eLearning;

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
    @Column(name = "E_Learning_Choice")
    private Long id;

    @Column(nullable = false)
    private String choiceContent;

    @ManyToOne(fetch = FetchType.EAGER)
    private ELearningQuestion eLearningQuestion;
}
