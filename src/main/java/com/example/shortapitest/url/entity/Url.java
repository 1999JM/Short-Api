package com.example.shortapitest.url.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String shortUrl;    //변경된 url

    @Column(nullable = false)
    private String destination_url; //기존 url

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime created_date;  //생성 날짜는 오늘 날짜로 자동 생성됩니다.

    @Column(columnDefinition = "integer default 0")
    private int total_clicks ;  //클릭 횟수는 0회가 기본 값입니다.

    @Column(columnDefinition = "Null")
    private int lest_clicked;



}
