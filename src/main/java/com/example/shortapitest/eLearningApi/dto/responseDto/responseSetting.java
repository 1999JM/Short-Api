package com.example.shortapitest.eLearningApi.dto.responseDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class responseSetting {

    private String eLearningName;
    private String eLearningAlias;
    private String deleted;
}
