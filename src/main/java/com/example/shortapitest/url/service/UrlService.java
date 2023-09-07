package com.example.shortapitest.url.service;

import com.example.shortapitest.url.dto.RequestUrl;
import com.example.shortapitest.url.dto.RequestUrlDetail;
import com.example.shortapitest.url.dto.ResponseUrl;
import com.example.shortapitest.url.entity.Url;
import com.example.shortapitest.url.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UrlService {

    private final UrlRepository urlRepository;

    //url을 생성합니다.
    @Transactional
    public ResponseUrl createUrl(RequestUrl res) {

        Long count = urlRepository.count();

        //pk 값을 가져오고 문자열로

        LocalDateTime now = LocalDateTime.now();
        String str = now.toString().replaceAll("[-,.:]", "")+String.valueOf(count);

        Url url  = Url.builder()
                .destinationUrl(res.getUrl())
                .shortUrl("http://localhpst:8989/" +str)
                .build();

        urlRepository.save(url);

        return ResponseUrl.builder()
                .id(url.getId())
                .destinationUrl(url.getDestinationUrl())
                .shortUrl(url.getShortUrl())
                .build();
    }

}
