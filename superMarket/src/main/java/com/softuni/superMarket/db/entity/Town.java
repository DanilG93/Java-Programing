package com.softuni.superMarket.db.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "towns")
public class Town {
    @Id
    @Column(nullable = false,unique = true)
    private String id;
    @Column(nullable = false)
    @NotNull(message = "Name cannot be null")
    private String name;

    public Town(String name) {
        this.name = name;
    }
}
