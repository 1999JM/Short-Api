package com.example.shortapitest.url.service;

import com.example.shortapitest.url.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UrlService {

    private final UrlRepository urlRepository;

    public String searchUrl(String url){

        //urlRepository.findByUrl();

        return "";
    }
}
