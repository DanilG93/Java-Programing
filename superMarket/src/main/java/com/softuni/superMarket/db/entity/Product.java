package com.softuni.superMarket.db.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @Column(nullable = false, unique = true)
    private String id;
    @Column(name = "best_before", nullable = false)
    private LocalDate bestBefore;
    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    @NotNull(message = "Name cannot be null")
    @Size(min = 2, message = "Name must be minimum two characters")
    private String name;

    @Column(nullable = false)
    @Positive(message = "Price mast be positive number")
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany
    @JoinTable(
            name = "products_shops",
            joinColumns = @JoinColumn(name = "products_id"),
            inverseJoinColumns = @JoinColumn(name = "shops_id"))
    List<Shop> shops;

    public Product(LocalDate bestBefore, String description, String name, BigDecimal price, Category category, List<Shop> shops) {
        this.bestBefore = bestBefore;
        this.description = description;
        this.name = name;
        this.price = price;
        this.category = category;
        this.shops = shops;
    }
}
