package com.example.shortapitest.url.controller;

import com.example.shortapitest.url.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/url")
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    //사용자가 url 변경 요청을 했습니다.
    @PostMapping("/{url}")
    public ResponseEntity<Boolean> changeUrl(@RequestParam @ String url){

        String testUrl = "https://www.google.com/search?q=%E3%85%8E&oq=%E3%85%8E&gs_lcrp=EgZjaHJvbWUyBggAEEUYOdIBBzY1N2owajeoAgCwAgA&sourceid=chrome&ie=UTF-8";
        urlService.searchUrl(testUrl);
        return ResponseEntity.ok().build();
    }
}
