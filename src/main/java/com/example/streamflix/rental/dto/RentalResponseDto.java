package com.example.streamflix.rental.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record RentalResponseDto(
        UUID id,
        UUID userId,
        UUID movieId,
        String status,
        BigDecimal price,
        OffsetDateTime expiresAt
) {}
