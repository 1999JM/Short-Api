package com.example.shortapitest.access.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Url {
    @Id
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
}
