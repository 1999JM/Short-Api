package com.example.shortapitest.url.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String shortUrl;    //변경된 url

    @Column(nullable = false)
    private String destinationUrl; //기존 url

    @Column(updatable = false)
    private LocalDateTime createdDate;  //생성 날짜는 오늘 날짜로 자동 생성됩니다.

    @Column(columnDefinition = "integer default 0")
    private int totalClicks ;  //클릭 횟수는 0회가 기본 값입니다.

    @Column(columnDefinition = "Null")
    private LocalDateTime lestClicked; //마지막으로 클릭한 날짜

}
