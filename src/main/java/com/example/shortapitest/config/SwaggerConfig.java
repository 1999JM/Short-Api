package com.example.shortapitest.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@OpenAPIDefinition(
        info = @Info(title = "DreamTrial40 Portal API 명세서",
                //description = "DreamTrial40 Portal API 명세서",
                version = "v1"))
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi OpenApi() {
        String[] paths = {"/**"};

        return GroupedOpenApi.builder()
                .group("DreamTrial40 API v1")
                .pathsToMatch(paths)
                .build();
    }
}