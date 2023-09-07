package com.example.shortapitest.url.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class RequestUrl {

    @NotNull(message = "url 입력은 필수 입니다.")
    @Pattern(regexp = "")

}
