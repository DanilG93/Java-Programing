package com.softuni.exercise.SpringBootAndSpringMVC.db.entity;

import com.softuni.exercise.SpringBootAndSpringMVC.db.entity.enums.Level;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "routes")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(name = "gpx_coordinates", columnDefinition = "LONGTEXT")
    private String gxpCoordinates;
    @Enumerated(value = EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(225)")
    private Level level;
    private String name;
    @Column(name = "video_url")
    private String videoUrl;

    @OneToMany(mappedBy = "route")
    private List<Picture> pictures;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;


    @ManyToMany
    @JoinTable(
            name = "routes_categories",
            joinColumns = @JoinColumn(name = "route_entity_id"),
            inverseJoinColumns = @JoinColumn(name = "categories_id")
    )
    private List<Category> categories;


}
