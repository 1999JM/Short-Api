package com.example.shortapitest.eLearningApi.controller;

import com.example.shortapitest.eLearningApi.dto.request.ELContentsDto;
import com.example.shortapitest.eLearningApi.dto.request.ELQuestionDto;
import com.example.shortapitest.eLearningApi.dto.request.ELSettingDto;
import com.example.shortapitest.eLearningApi.dto.response.ELContentsReturnDto;
import com.example.shortapitest.eLearningApi.dto.response.ELQuestionReturnDto;
import com.example.shortapitest.eLearningApi.dto.response.PageReturnDto;
import com.example.shortapitest.eLearningApi.service.ELearningService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/eLearning")
@Tag(name = "이러닝 API", description = "이러닝 등록")
public class ELearningController {
    private final ELearningService eLearningService;

    @Operation(summary = "이러닝 Setting 등록")
    @PostMapping("/settings")
    public void createELearningSetting(@RequestPart ELSettingDto elSettingDto
                                ,@RequestPart @Parameter(schema = @Schema(name = "json", type = "string", format = "binary")) MultipartFile logoImage
                                ,@RequestPart @Parameter(schema = @Schema(name = "json", type = "string", format = "binary")) MultipartFile coverImage
                                ){
        eLearningService.eLearningSettingCreate(elSettingDto, logoImage, coverImage);
    }

    @Operation(summary = "이러닝 Contents 등록")
    @PostMapping("/contents")
    public void createELearningContents(@RequestPart ELContentsDto elContentsDto
            , @RequestPart @Parameter(schema = @Schema(name = "json", type = "string", format = "binary")) List<MultipartFile> menuImageList){
        eLearningService.eLearningContentsCreate(elContentsDto, menuImageList);
    }

    @Operation(summary = "이러닝 Question 등록")
    @PostMapping("/questions")
    public void createELearningQuestion(@RequestPart ELQuestionDto elQuestionDto
            , @RequestPart @Parameter(schema = @Schema(name = "json", type = "string", format = "binary")) List<MultipartFile> questionImageList){
        eLearningService.eLearningQuestionCreate(elQuestionDto, questionImageList);
    }

    // 이러닝 전체 조회 최신 생성순으로 조회 합니다.
    // 조건 검색 항목은 여러개 입니다. 생성 날짜, 이러닝 네임 == 여러개로 받는 방법 문의 후 다시 컨펌 받기
    // 리스트로 리턴해줘야 하는 값들 이러닝 네임, 이러닝 별칭, 이러닝, 삭제 처리 여부
    @Operation(summary = "이러닝 전체 조회")
    @GetMapping(value = "/search")
    public PageReturnDto selectELearningSettingPage(@RequestParam(defaultValue = "1") int page
            , @RequestParam(defaultValue = "10") int rows
            , @RequestParam String startDate
            , @RequestParam String endDate
            , @RequestParam String keyword ) {
        // page = 현재 페이지 번호를 받습니다.
        // rows = 한 페이지에 보여지는 행의 개수
        // filter = 검색 조건
        // searchDetail = 검색 키워드
        return eLearningService.selectELearningSettingPage(PageRequest.of(page , rows), startDate, endDate, keyword);
    }

    //이러닝 콘텐츠 조회
    //데이터 많이 넣고 테스트
    @Operation(summary = "이러닝 Contents 조회")
    @GetMapping(value = "/contents")
    public ELContentsReturnDto selectELearningContents(@RequestParam Long eLSettingId) {
        return eLearningService.selectELearningContent(eLSettingId);
    }

    //이러닝 Question 조회
    @Operation(summary = "이러닝 Question 조회")
    @GetMapping(value = "/question")
    public ELQuestionReturnDto selectELearningQuestion(@RequestParam Long eLSettingId) {
        return eLearningService.selectELearningQuestion(eLSettingId);
    }

    //해당 ELearning Setting 수정
    @Operation(summary = "이러닝 Setting 업데이트")
    @PutMapping("/settings")
    public void updateELearningSetting(@RequestPart ELSettingDto elSettingDto
            ,@RequestPart @Parameter(schema = @Schema(name = "json", type = "string", format = "binary")) MultipartFile logoImage
            ,@RequestPart @Parameter(schema = @Schema(name = "json", type = "string", format = "binary")) MultipartFile coverImage){
        //이러닝 수정 모든 데이터를 받는다.
        eLearningService.eLearningSettingUpdate(elSettingDto, logoImage, coverImage);
    }

    //해당 ELearning Contents 수정
    @Operation(summary = "이러닝 Contents 업데이트")
    @PutMapping("/contents")
    public void updateELearningContents(@RequestPart ELContentsDto elContentsDto
            , @RequestPart @Parameter(schema = @Schema(name = "json", type = "string", format = "binary")) List<MultipartFile> menuImageList){
        eLearningService.eLearningContentsUpdate(elContentsDto, menuImageList );
    }

/*    @Operation(summary = "이러닝 Setting 삭제")
    @DeleteMapping("/setting/{}")
    public void deleteELearningSetting(@RequestParam() long eLearningSettingId){
        eLearningService.eLearningSettingDelete(eLearningSettingId);
    }

    @Operation(summary = "이러닝 Setting 복구")
    @PostMapping("/setting")
    public void createELearningQuestion(@RequestPart long eLearningSettingId){
        eLearningService.eLearningSettingRecovery(eLearningSettingId);
    }*/
}
