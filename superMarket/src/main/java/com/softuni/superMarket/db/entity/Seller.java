package com.softuni.superMarket.db.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "sellers")
public class Seller {
    @Id
    @Column(nullable = false,unique = true)
    private String id;

    @Column(name = "first_name", nullable = false)
    @NotNull(message = "Name cannot be null")
    @Size(min = 2, message = "Name must be minimum two characters")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotNull(message = "Name cannot be null")
    @Size(min = 2, message = "Name must be minimum two characters")
    private String lastName;

    @Column(nullable = false)
    @NotNull(message = "Name cannot be null")
    @Min(value = 18, message = "Age should not be less than 18")
    private Integer age;

    @Column(nullable = false)
    @NotNull(message = "Name cannot be null")
    @Positive(message = "Price mast be positive number")
    private BigDecimal salary;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Seller manager;

    public Seller(String firstName, String lastName, Integer age, BigDecimal salary, Shop shop) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.salary = salary;
        this.shop = shop;
    }
}
