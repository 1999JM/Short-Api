package com.example.shortapitest.eLearningApi.entity.image;

import com.example.shortapitest.eLearningApi.entity.eLearning.question.ELearningQuestion;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
public class QuestionImage extends BaseImage{

    @Id
    @Column(name = "question_image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "e_learning_question_id", updatable = false)
    private ELearningQuestion eLearningQuestion;

    public static QuestionImage createLogoImage(String newQuestionImageName, String originalFilename, String questionImageLocation, ELearningQuestion eLearningQuestion) {
        QuestionImage questionImage = QuestionImage.builder()
                .filename(newQuestionImageName)
                .fileOriName(originalFilename)
                .fileUrl(questionImageLocation)
                .eLearningQuestion(eLearningQuestion)
                .deleted(false)
                .build();
        return questionImage;
    }
}
