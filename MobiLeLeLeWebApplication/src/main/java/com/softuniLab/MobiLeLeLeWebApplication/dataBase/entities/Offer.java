package com.softuniLab.MobiLeLeLeWebApplication.dataBase.entities;

import com.softuniLab.MobiLeLeLeWebApplication.dataBase.entities.enums.Engine;
import com.softuniLab.MobiLeLeLeWebApplication.dataBase.entities.enums.Transmission;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "offers")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT",nullable = false)
    private String description;
    @Enumerated(value = EnumType.ORDINAL)
    @Column(nullable = false)
    private Engine engine;
    @Column(name = "image_url",nullable = false)
    private String imageUrl;
    @Column(nullable = false)
    private Long mileage;
    @Column(nullable = false)
    private BigDecimal price;
    @Enumerated(value = EnumType.ORDINAL)
    @Column(nullable = false)
    private Transmission transmission;
    @Column(nullable = false)
    private Integer year;
    @Column(nullable = false)
    private LocalDateTime created;
    @Column(nullable = false)
    private LocalDateTime modified;

    @ManyToOne
    @JoinColumn(name = "model_id",nullable = false)
    private Model model;

    @ManyToOne
    @JoinColumn(name = "seller_id",nullable = false)
    private User seller;

    public Offer(String description, Engine engine, String imageUrl,
                 Long mileage, BigDecimal price, Transmission transmission, Integer year,
                 LocalDateTime created, LocalDateTime modified, Model model, User seller) {
        this.description = description;
        this.engine = engine;
        this.imageUrl = imageUrl;
        this.mileage = mileage;
        this.price = price;
        this.transmission = transmission;
        this.year = year;
        this.created = created;
        this.modified = modified;
        this.model = model;
        this.seller = seller;
    }
}
