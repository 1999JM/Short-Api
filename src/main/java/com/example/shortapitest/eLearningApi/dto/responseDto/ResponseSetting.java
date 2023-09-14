package com.example.shortapitest.eLearningApi.dto.responseDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseSetting {

    private String eLearningName;
    private String eLearningAlias;
    private String deleted;
}
