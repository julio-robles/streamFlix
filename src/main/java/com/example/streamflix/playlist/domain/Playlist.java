package com.example.streamflix.playlist.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Playlist {

    @Id @GeneratedValue
    private UUID id;

    private UUID userId;

    private String name;

    /** número de elementos que contiene la playlist */
    @Column(nullable = false)
    private int itemCount = 0;

    private OffsetDateTime createdAt = OffsetDateTime.now();

    // constructor útil para tests
    public Playlist(UUID userId, String name, int itemCount, OffsetDateTime createdAt) {
        this(null, userId, name, itemCount, createdAt);
    }
}
