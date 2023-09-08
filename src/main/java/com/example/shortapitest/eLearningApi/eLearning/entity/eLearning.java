package com.example.shortapitest.eLearningApi.eLearning.entity;

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
public class eLearning {

    @Id
    @Column(name = "eLearning_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "eLearning_name", nullable = false)
    private String name;

    @Column(name = "eLearning_alias", nullable = false)
    private String alias;


}
