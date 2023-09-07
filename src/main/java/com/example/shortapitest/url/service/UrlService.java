package com.example.shortapitest.url.service;

import com.example.shortapitest.url.dto.RequestUrl;
import com.example.shortapitest.url.dto.ResponseUrl;
import com.example.shortapitest.url.entity.Url;
import com.example.shortapitest.url.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;

@Service
@RequiredArgsConstructor
public class UrlService {

    private final UrlRepository urlRepository;

    //url이 데이터 베이스에 존재하는 확인합니다.
    public ResponseUrl searchUrl(RequestUrl res) {

        String testAddress = "https://www.google.com/search?q=%E3%85%8E&oq=%E3%85%8E&gs_lcrp=EgZjaHJvbWUyBggAEEUYOdIBBzY1N2owajeoAgCwAgA&sourceid=chrome&ie=UTF-8";

        Url url = urlRepository.findByUrl(testAddress);

        if (url == null) {
            return createUrl(testAddress);
        }else {
            return ResponseUrl.builder()
                    .destination_url(url.getDestination_url())
                    .shortUrl(url.getShortUrl())
                    .build();
        }
    }

    //url을 생성합니다.
    public ResponseUrl createUrl(String testAddress) {

        //여기서는 변경할 url을 생성하는 로직을 처리해야합니다.
        Long count = urlRepository.count();

        DecimalFormat pattern = new DecimalFormat("00");
        String str = pattern.format(count);

        Url url  = Url.builder()
            .destination_url(testAddress)
            .shortUrl("http://localhpst:8989/" +str)
            .build();
ß
        urlRepository.save(url);

        return ResponseUrl.builder()
                .shortUrl(url.getShortUrl())
                .destination_url(testAddress)
                .build();
    }


}
