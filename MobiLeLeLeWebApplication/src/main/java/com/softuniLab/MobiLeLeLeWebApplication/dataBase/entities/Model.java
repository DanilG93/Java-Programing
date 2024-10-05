package com.softuniLab.MobiLeLeLeWebApplication.dataBase.entities;

import com.softuniLab.MobiLeLeLeWebApplication.dataBase.entities.enums.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "models")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Enumerated(value = EnumType.ORDINAL)
    @Column(nullable = false)
    private Category category;
    @Column(name = "image_url", nullable = false)
    private String imageUrl;
    @Column(name = "start_year", nullable = false)
    private Integer startYear;
    @Column(name = "end_year", nullable = false)
    private Integer endYear;
    @Column(nullable = false)
    private LocalDateTime created;
    @Column(nullable = false)
    private LocalDateTime modified;
    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    public Model(String name, Category category,
                 String imageUrl, Integer startYear,
                 Integer endYear, LocalDateTime created,
                 LocalDateTime modified, Brand brand) {
        this.name = name;
        this.category = category;
        this.imageUrl = imageUrl;
        this.startYear = startYear;
        this.endYear = endYear;
        this.created = created;
        this.modified = modified;
        this.brand = brand;
    }
}

