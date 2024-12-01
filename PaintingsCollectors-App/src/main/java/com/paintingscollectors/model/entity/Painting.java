package com.paintingscollectors.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "paintings")
@Builder
public class Painting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40)
    private String name;

    @Column(nullable = false, length = 30)
    private String author;

    @ManyToOne
    @JoinColumn(name = "style_id", nullable = false)
    private Style style;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @Column(nullable = false, length = 150)
    private String imageUrl;

    @Column(nullable = false)
    private boolean isFavorite;

    @Column(nullable = false)
    private int votes;
}