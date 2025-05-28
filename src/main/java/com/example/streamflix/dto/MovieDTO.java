package com.example.streamflix.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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


}