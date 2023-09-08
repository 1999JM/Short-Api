package com.example.shortapitest.eLearningApi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ELearningMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "e_learning_menu_id")
    private Long id;

    @Column(nullable = false)
    private String menuName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ELearningMenu")
    private List<ELearningImage> menuImage;

}
