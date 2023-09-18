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

    public static ELQuestionDetailReturnDto setQuestionDetailReturnDto(ELearningQuestion questionDto, ELImageReturnDto elImageReturnDto) {
        ELQuestionDetailReturnDto elQuestionDetailReturnDto = new ELQuestionDetailReturnDto();

        elQuestionDetailReturnDto.setQuestionId(questionDto.getId());
        elQuestionDetailReturnDto.setQuestionName(questionDto.getQuestionName());
        elQuestionDetailReturnDto.setAnswerCheckType(questionDto.getAnswerType().toString());
        elQuestionDetailReturnDto.setElImageReturnDto(elImageReturnDto);

        return elQuestionDetailReturnDto;
    }

    public void addELChoiceReturnDto(ELChoiceReturnDto elChoiceReturnDto) {
        this.choiceDtoList.add(elChoiceReturnDto);
    }
}
