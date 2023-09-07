package com.example.shortapitest.url.service;

import com.example.shortapitest.url.dto.RequestUrl;
import com.example.shortapitest.url.dto.RequestUrlDetail;
import com.example.shortapitest.url.dto.ResponseUrl;
import com.example.shortapitest.url.entity.Url;
import com.example.shortapitest.url.repository.UrlRepository;
import jakarta.persistence.EntityNotFoundException;
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

        LocalDateTime now = LocalDateTime.now();
        String str = now.toString().replaceAll("[-,.:]", "")+String.valueOf(count);

        Url url  = Url.builder()
                .destination_url(res.getUrl())
                .shortUrl("http://localhpst:8989/" +str)
                .build();

        urlRepository.save(url);

        return ResponseUrl.builder()
                .id(url.getId())
                .destination_url(url.getDestination_url())
                .shortUrl(url.getShortUrl())
                .build();
    }

    public void searchUrlDetail(RequestUrlDetail req) {
       Url url = urlRepository.findById(req.getId()).orElseThrow(() -> new EntityNotFoundException("url "));

    }
}
