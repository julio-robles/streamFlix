package com.example.streamflix.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

/**
 * DTO de entrada para crear una reseña de película.
 */
public record ReviewCreateDto(

        @NotNull UUID movieId,

        @Min(1) @Max(5)
        int stars,

        @Size(max = 500)
        String comment
) {}