package com.example.shortapitest.url.entity;

import com.example.shortapitest.access.entity.AccessLog;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Url {

    @Id
    @Column(name = "URL_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String shortUrl;    //변경된 url

    @Column(nullable = false)
    private String destinationUrl; //기존 url

    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;  //생성 날짜는 오늘 날짜로 자동 생성됩니다.

    @Column
    private long totalClicks ;  //클릭 횟수는 0회가 기본 값입니다.

    @Column
    private LocalDateTime lestClicked; //마지막으로 클릭한 날짜

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCESS_LOG_ID")
    private List<AccessLog> accessLog;

    public void setShortUrl(String creatUrl){
        this.shortUrl = creatUrl;
    }
    public void setClicked(long totalClicks, AccessLog accessLog){
        this.totalClicks = totalClicks + 1;
        this.lestClicked = LocalDateTime.now();
        this.accessLog.add(accessLog);
    }
}
