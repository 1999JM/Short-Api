package com.example.shortapitest.eLearningApi.controller;

import com.example.shortapitest.eLearningApi.dto.requestDto.ELearningContentsDto;
import com.example.shortapitest.eLearningApi.dto.requestDto.ELearningQuestionDto;
import com.example.shortapitest.eLearningApi.dto.requestDto.ELearningSettingDto;
import com.example.shortapitest.eLearningApi.dto.responseDto.ResponseSetting;
import com.example.shortapitest.eLearningApi.service.ELearningService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

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

    @Operation(summary = "이러닝 Question 등록")
    @PostMapping("/create-question")
    public void createELearningQuestion(@RequestPart ELearningQuestionDto eLearningQuestionDto
            , @RequestPart @Parameter(schema = @Schema(name = "json", type = "string", format = "binary")) List<MultipartFile> questionImages){
        eLearningService.eLearningQuestionCreate(eLearningQuestionDto, questionImages);
    }

    // 이러닝 전체 조회 최신 생성순으로 조회 합니다.
    // 페이징처리는 제외 하였습니다. 만약 페이지 네이션을 구현할경우 rows의 값과 현재 페이지 정보를 받아 페이지징 처리할 것.
    // 리스트로 리턴해줘야 하는 값들 이러닝 네임, 이러닝 별칭, 이러닝, 삭제 처리 여부
    @Operation(summary = "이러닝 전체 조회")
    @PostMapping(value = {"/select-setting","/select-setting/{page}"})
    public List<ResponseSetting> selectELearningSettingPage(@PathVariable("page") Optional<Integer> page, @RequestPart int rows){
        // page = 현재 페이지 번호를 받습니다.
        // rows = 한 페이지에 보여지는 행의 개수

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, rows);
        eLearningService.selectELearningSettingPage(pageable);

        return null;
    }





    @Operation(summary = "이러닝 Setting 삭제")
    @DeleteMapping("/delete-setting")
    public void deleteELearningSetting(@RequestPart long eLearningSettingId){
        eLearningService.eLearningSettingDelete(eLearningSettingId);
    }

    @Operation(summary = "이러닝 Setting 복구")
    @PostMapping("/Recovery-setting")
    public void createELearningQuestion(@RequestPart long eLearningSettingId){
        eLearningService.eLearningSettingRecovery(eLearningSettingId);
    }
}
