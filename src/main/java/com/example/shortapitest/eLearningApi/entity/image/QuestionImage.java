package com.example.shortapitest.eLearningApi.entity.image;

import com.example.shortapitest.eLearningApi.entity.eLearning.question.ELearningQuestion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionImage extends BaseImage{

    @Id
    @Column(name = "question_image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
