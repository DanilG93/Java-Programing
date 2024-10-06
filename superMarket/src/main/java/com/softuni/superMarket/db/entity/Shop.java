package com.softuni.superMarket.db.entity;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "shops")
public class Shop {
    @Id
    @Column(nullable = false,unique = true)
    private String id;

    @Column(nullable = false,unique = true)
    @NotNull(message = "Name cannot be null")
    @Size(min = 2, message = "Name must be minimum two characters")
    private String address;

    @Column(nullable = false,unique = true)
    @NotNull(message = "Name cannot be null")
    @Size(min = 2, message = "Name must be minimum two characters")
    private String name;

    @ManyToOne
    @JoinColumn(name = "town_id")
    private Town town;

    @ManyToMany(mappedBy = "shops")
    private List<Product> products;

    public Shop(String address, String name, Town town, List<Product> products) {
        this.address = address;
        this.name = name;
        this.town = town;
        this.products = products;
    }
}
