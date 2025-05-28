package com.example.streamflix.dto;

import java.time.LocalDate;
import java.util.List;

public class MovieDTO {
    private Long id;
    private String title;
    private String description;
    private Integer releaseYear;
    private String director;
    private List<String> genres;
    private Integer durationMinutes;
    private String ageRating;
    private String coverImageUrl;
    private Double averageRating;
    private LocalDate addedDate;

    // Getters y setters
}