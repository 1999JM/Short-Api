package com.example.shortapitest.urlApi.url.service;

import com.example.shortapitest.urlApi.access.entity.AccessLog;
import com.example.shortapitest.urlApi.access.repository.AccessLogRepository;
import com.example.shortapitest.urlApi.url.dto.RequestUrl;
import com.example.shortapitest.urlApi.url.dto.ResponseUrl;
import com.example.shortapitest.urlApi.url.entity.Url;
import com.example.shortapitest.urlApi.url.repository.UrlRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UrlService {

    private final UrlRepository urlRepository;
    private final AccessLogRepository accessLogRepository;

    //url을 생성합니다.
    @Transactional
    public ResponseUrl createUrl(RequestUrl res) {

        Url url  = Url.builder()
                .destinationUrl(res.getUrl())
                .build();
        Url savedUrl = urlRepository.save(url);
        
        //인코딩
        String generatedString = RandomStringUtils.randomAlphanumeric(10);
        String str = "http://localhost:9033/jmApi/" + generatedString + savedUrl.getId();

        url.setShortUrl(str);

        return ResponseUrl.builder()
                .id(savedUrl.getId())
                .destinationUrl(savedUrl.getDestinationUrl())
                .shortUrl(savedUrl.getShortUrl())
                .build();
    }

    @Transactional
    public String searchUrl(String id, Map<String, String> map) {

        String urlId = id.substring(10);
        Url url = urlRepository.findById(Long.parseLong(urlId)).orElseThrow(() ->  new EntityNotFoundException("url 입력 실패"));

        String destinationUrl = url.getDestinationUrl();

        AccessLog accessLog = AccessLog.builder()
                .ip(map.get("ip"))
                .referrerUrl(destinationUrl)
                .userAgent(map.get("header"))
                .url(url)
                .build();

        AccessLog saveAccessLog = accessLogRepository.save(accessLog);

        url.setClicked(url.getTotalClicks(),saveAccessLog);

        return destinationUrl;
    }
}
