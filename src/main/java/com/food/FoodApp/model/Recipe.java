package com.food.FoodApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "recipe")
public class Recipe {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Column
    @Getter @Setter private String name;

    @Column
    @Getter @Setter private String time;

    @Column
    @Getter @Setter private Integer portions;

    @Column
    @Getter @Setter private String ingredients;

    @Column
    @Getter @Setter private String steps;

    @Column
    @Getter @Setter private Boolean isPublic;

    @JsonIgnore
    @ManyToOne
    @JoinColumn (name = "category_id")
    @Getter @Setter private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Getter @Setter private User user;

}