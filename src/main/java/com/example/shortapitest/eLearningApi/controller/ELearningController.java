package com.example.shortapitest.eLearningApi.controller;

import com.example.shortapitest.eLearningApi.dto.ELearningContentsDto;
import com.example.shortapitest.eLearningApi.dto.ELearningSettingDto;
import com.example.shortapitest.eLearningApi.service.ELearningService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/eLearning")
@Tag(name = "이러닝 API", description = "이러닝 등록")
public class ELearningController {
    private final ELearningService eLearningService;

    @Operation(summary = "이러닝 Setting 등록")
    @PostMapping("/create-setting")
    public void createELearningSetting(@RequestPart ELearningSettingDto ELearningSettingDto
                                ,@RequestPart @Parameter(schema = @Schema(name = "json", type = "string", format = "binary")) MultipartFile logoImage
                                ,@RequestPart @Parameter(schema = @Schema(name = "json", type = "string", format = "binary")) MultipartFile coverImage
                                ){
        eLearningService.eLearningSettingCreate(ELearningSettingDto, logoImage, coverImage);
    }

    @Operation(summary = "이러닝 Contents 등록")
    @PostMapping("/create-contents")
    public void createELearningContents(@RequestPart ELearningContentsDto eLearningContentsDto
            , @RequestPart @Parameter(schema = @Schema(name = "json", type = "string", format = "binary")) List<MultipartFile> menuImage){
        eLearningService.eLearningContentsCreate(eLearningContentsDto, menuImage);
    }

}
