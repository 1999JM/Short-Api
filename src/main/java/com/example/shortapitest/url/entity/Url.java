package com.example.shortapitest.url.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Entity
@Getter
@Builder
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String shortUrl;

    @Column(nullable = false)
    private String destination_url;
    private String short_url;

    @Column(nullable = false)
    private Date created_date;

    @Column(nullable = false)
    private int total_clicks ;
    @Column(nullable = false)
    private int lest_clicked;

    public Url() {

    }
}
