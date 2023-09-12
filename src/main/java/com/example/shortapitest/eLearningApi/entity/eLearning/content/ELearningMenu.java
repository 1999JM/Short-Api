package com.example.shortapitest.eLearningApi.entity.eLearning.content;

import com.example.shortapitest.eLearningApi.entity.image.MenuImage;
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
public class ELearningMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "e_learning_menu_id")
    private Long id;

    @Column(nullable = false)
    private String menuName;

    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuSequence;


    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "menu_image_id")
    private MenuImage menuImage;


    @ManyToOne(fetch = FetchType.LAZY)
    private ELearningCategory eLearningCategory;

}
