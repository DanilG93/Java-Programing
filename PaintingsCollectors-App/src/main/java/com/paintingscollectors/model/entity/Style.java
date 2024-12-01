package com.paintingscollectors.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "styles")
public class Style {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private StyleName name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "style", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Painting> paintings = new ArrayList<>();

    public Style(String name, String description) {
        this.name = StyleName.valueOf(name);
        this.description = description;
    }

}
