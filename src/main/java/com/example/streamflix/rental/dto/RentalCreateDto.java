package com.example.streamflix.rental.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.UUID;

/**
 * DTO de entrada para crear un alquiler.
 */
public record RentalCreateDto(
        @NotNull UUID userId,
        @NotNull UUID movieId,
        @Pattern(regexp = "HD|4K") String resolution
) {}

