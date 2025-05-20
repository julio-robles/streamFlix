package com.example.streamflix.rental.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Rental {

    @Id @GeneratedValue
    private UUID id;

    private UUID userId;
    private UUID movieId;

    @Enumerated(EnumType.STRING)
    private RentalStatus status;

    private BigDecimal price;
    private OffsetDateTime createdAt;
    private OffsetDateTime expiresAt;
}