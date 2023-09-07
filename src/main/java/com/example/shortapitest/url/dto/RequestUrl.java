package com.example.shortapitest.url.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class RequestUrl {

    @NotNull(message = "url 입력은 필수 입니다.")
    @Pattern(regexp = "^((((https?|ftps?|gopher|telnet|nntp)://)|(mailto:|news:))" +
            "(%[0-9A-Fa-f]{2}|[-()_.!~*';/?:@&=+$,A-Za-z0-9])+)" +
            "([).!';/?:,][[:blank:]])?$", message = "정확한 값을 입력 해주세요.")
    private String url;

}
