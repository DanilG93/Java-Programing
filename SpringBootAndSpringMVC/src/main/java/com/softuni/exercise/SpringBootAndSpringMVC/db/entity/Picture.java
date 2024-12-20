package com.softuni.exercise.SpringBootAndSpringMVC.db.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "pictures")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String url;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;


}
