package com.example.shortapitest.access.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlController {

    //url 주소를 받아옵니다.
    @GetMapping("get")
    public ResponseEntity<Boolean> getUrl(){
        String url = "https://www.google.com/search?q=%E3%85%8E&oq=%E3%85%8E&gs_lcrp=EgZjaHJvbWUyBggAEEUYOdIBBzY1N2owajeoAgCwAgA&sourceid=chrome&ie=UTF-8";

        return ResponseEntity.ok().build();
    }
}
