package com.softuni.superMarket.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @Column(nullable = false,unique = true)
    private String id;

    @Column(nullable = false)
    @NotNull(message = "Name cannot be null")
    @Size(min = 2, message = "Name must be minimum two characters")
    private String name;

    public Category(String name) {
        this.name = name;
    }
}
