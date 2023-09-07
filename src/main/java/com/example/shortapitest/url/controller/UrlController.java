package com.example.shortapitest.url.controller;

import com.example.shortapitest.url.dto.RequestUrl;
import com.example.shortapitest.url.dto.RequestUrlDetail;
import com.example.shortapitest.url.dto.ResponseUrl;
import com.example.shortapitest.url.service.UrlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/url")
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    //사용자가 url 변경 요청을 했습니다.
    @PostMapping("/change")
    public ResponseEntity<ResponseUrl> changeUrl(@RequestBody @Validated RequestUrl req){
        return ResponseEntity.ok(urlService.createUrl(req));
    }

    //url의 pk id
    @GetMapping("/detail")
    public void detailUrl(@RequestBody RequestUrlDetail req){
        urlService.searchUrlDetail(req);
        return;
    }


}
