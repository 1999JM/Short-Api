package com.example.shortapitest.eLearningApi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestELearning {

    @Schema(description = "이러닝 이름")
    private String eLearningName;

    private String eLearningAlias;

    //로고 파일
    //커버 파일
    //정답
    //정답 표시 여부 Boolean

}
