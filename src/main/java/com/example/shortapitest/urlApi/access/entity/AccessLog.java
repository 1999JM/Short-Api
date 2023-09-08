package com.example.shortapitest.urlApi.access.entity;

import com.example.shortapitest.urlApi.url.entity.Url;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccessLog {

    @Id
    @Column(name = "ACCESS_LOG_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ip;

    @Column(nullable = false)
    private String userAgent;

    @Column(nullable = false)
    private String referrerUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    private Url url;

}
