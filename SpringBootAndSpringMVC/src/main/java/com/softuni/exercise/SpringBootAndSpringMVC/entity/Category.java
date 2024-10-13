package com.softuni.exercise.SpringBootAndSpringMVC.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String name;

    @ManyToMany(mappedBy = "categories")
    private List<Route> routes;
}
