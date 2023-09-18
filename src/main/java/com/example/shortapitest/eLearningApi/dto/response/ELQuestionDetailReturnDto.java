package com.example.shortapitest.eLearningApi.dto.response;

import com.example.shortapitest.eLearningApi.entity.eLearning.question.ELearningQuestion;
import com.example.shortapitest.eLearningApi.entity.image.QuestionImage;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ELQuestionDetailReturnDto {

    private Long questionId;

    private String questionName;

    private String answerCheckType;

    private ELImageReturnDto elImageReturnDto;

    private List<ELChoiceReturnDto> choiceDtoList = new ArrayList<>();

    public static ELQuestionDetailReturnDto setQuestionDetailReturnDto(ELearningQuestion eLearningQuestion ) {
        ELQuestionDetailReturnDto elQuestionDetailReturnDto = new ELQuestionDetailReturnDto();

        elQuestionDetailReturnDto.setQuestionId(eLearningQuestion.getId());
        elQuestionDetailReturnDto.setQuestionName(eLearningQuestion.getQuestionName());
        elQuestionDetailReturnDto.setAnswerCheckType(eLearningQuestion.getAnswerType().toString());
        elQuestionDetailReturnDto.setElImageReturnDto(ELImageReturnDto.setELImageReturnDto(eLearningQuestion.getQuestionImage()));
        elQuestionDetailReturnDto.setChoiceDtoList(
                eLearningQuestion.getELearningChoiceList().stream().map(ELChoiceReturnDto::setELChoiceReturnDto).toList()
        );


        return elQuestionDetailReturnDto;
    }

    public void addELChoiceReturnDto(ELChoiceReturnDto elChoiceReturnDto) {
        this.choiceDtoList.add(elChoiceReturnDto);
    }
}
