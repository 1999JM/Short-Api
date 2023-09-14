package com.example.shortapitest.eLearningApi.dto.responseDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseSetting {

    private Long id;
    private String eLearningName;
    private String eLearningAlias;
    private boolean deleted;
}
