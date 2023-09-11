package com.example.shortapitest.eLearningApi.controller;

import com.example.shortapitest.eLearningApi.dto.RequestELearning;
import com.example.shortapitest.eLearningApi.dto.RequestELearningCategory;
import com.example.shortapitest.eLearningApi.dto.RequestELearningQuestion;
import com.example.shortapitest.eLearningApi.service.ELearningService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/eLearning")
@Tag(name = "이러닝 API", description = "이러닝 등록")
public class ELearningController {

    private final ELearningService eLearningService;

    @Operation(summary = "이러닝 등록")
    @PostMapping("/create")
    public void createELearning( @RequestPart @Parameter(schema = @Schema(name = "json", type = "string", format = "binary"))  RequestELearning requestELearning,
                                //@RequestPart List<RequestELearningCategory> requestELearningCategories,
                                //@RequestPart  List<RequestELearningQuestion> requestELearningQuestion,
                                @RequestPart  List<MultipartFile> multipartFile){

        System.out.printf("요청이 들어왔습니다.");
    }

}
