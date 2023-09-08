package com.example.shortapitest.urlApi.url.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUrl {

    private Long id;
    private String shortUrl;
    private String destinationUrl;

}
