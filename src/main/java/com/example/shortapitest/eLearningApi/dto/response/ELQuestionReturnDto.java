package com.example.shortapitest.eLearningApi.dto.response;

import com.example.shortapitest.eLearningApi.entity.eLearning.ELearningSetting;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ELQuestionReturnDto {
    private Long eLSettingId;

    private boolean wrongAnswerSkip;    //오답시 다음 문제 진행 여부 기본값: false

    private boolean displayAnswer;      // 정답 공개 여부 설정 기본값: false

    private int testPassScore;          //eLearning 수료 기준 0~100 소숫점은 반올림 수료시 Certification

    private List<ELQuestionDetailReturnDto> elQuestionReturnDetailDtoList = new ArrayList<>();

    public ELQuestionReturnDto(List<ELQuestionDetailReturnDto> elQuestionDetailReturnDtos, ELearningSetting eLearningSetting) {
        this.eLSettingId = eLearningSetting.getId();
        this.wrongAnswerSkip = eLearningSetting.isWrongAnswerSkip();
        this.displayAnswer = eLearningSetting.isDisplayAnswer();
        this.testPassScore = eLearningSetting.getTestPassScore();
        this.elQuestionReturnDetailDtoList = elQuestionDetailReturnDtos;
    }
}
