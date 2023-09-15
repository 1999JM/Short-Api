package com.example.shortapitest.eLearningApi.controller;

import com.example.shortapitest.eLearningApi.dto.request.create.ELContentsCreateDto;
import com.example.shortapitest.eLearningApi.dto.request.create.ELearningQuestionDto;
import com.example.shortapitest.eLearningApi.dto.request.create.ELSettingCreateDto;
import com.example.shortapitest.eLearningApi.dto.request.update.ELContentsUpdateDto;
import com.example.shortapitest.eLearningApi.dto.response.PageELSettingReturnDto;
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
    public void createELearningSetting(@RequestPart ELSettingCreateDto elSettingCreateDto
                                ,@RequestPart @Parameter(schema = @Schema(name = "json", type = "string", format = "binary")) MultipartFile logoImage
                                ,@RequestPart @Parameter(schema = @Schema(name = "json", type = "string", format = "binary")) MultipartFile coverImage
                                ){
        eLearningService.eLearningSettingCreate(elSettingCreateDto, logoImage, coverImage);
    }

    @Operation(summary = "이러닝 Contents 등록")
    @PostMapping("/create-contents")
    public void createELearningContents(@RequestPart ELContentsCreateDto eLearningContentsDto
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
    // 리스트로 리턴해줘야 하는 값들 이러닝 네임, 이러닝 별칭, 이러닝, 삭제 처리 여부
    @Operation(summary = "이러닝 전체 조회")
    @PostMapping(value = {"/select-setting","/select-setting/{page}"})
    public PageELSettingReturnDto selectELearningSettingPage(@RequestParam("page") Optional<Integer> page, @RequestPart int rows){
        // page = 현재 페이지 번호를 받습니다.
        // rows = 한 페이지에 보여지는 행의 개수
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, rows);
        return eLearningService.selectELearningSettingPage(pageable);
    }

    //해당 ELearning Setting 수정
    @Operation(summary = "이러닝 Setting 업데이트")
    @PutMapping("/update-setting")
    public void updateELearningSetting(@RequestPart ELSettingCreateDto eLearningSettingDto
            ,@RequestPart @Parameter(schema = @Schema(name = "json", type = "string", format = "binary")) MultipartFile logoImage
            ,@RequestPart @Parameter(schema = @Schema(name = "json", type = "string", format = "binary")) MultipartFile coverImage){
        //이러닝 수정 모든 데이터를 받는다.
        eLearningService.eLearningSettingUpdate(eLearningSettingDto, logoImage, coverImage);
    }

    //해당 ELearning Contents 수정
    @Operation(summary = "이러닝 Contents 업데이트")
    @PutMapping("/update-contents")
    public void updateELearningContents(@RequestPart ELContentsUpdateDto elContentsUpdateDto
            , @RequestPart @Parameter(schema = @Schema(name = "json", type = "string", format = "binary")) List<MultipartFile> menuImageList){

        eLearningService.eLearningContentsUpdate(elContentsUpdateDto, menuImageList );
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
