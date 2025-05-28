package com.example.streamflix.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "movies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "release_year")
    private Integer releaseYear;

    @Column
    private String director;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "age_rating", length = 10)
    private String ageRating;

    @Column(name = "cover_image_url", length = 255)
    private String coverImageUrl;

    @Column(name = "average_rating", precision = 3, scale = 1)
    private BigDecimal averageRating = BigDecimal.ZERO;

    @Column(name = "added_date")
    private LocalDateTime addedDate;

    // Si quieres mantener los géneros, puedes dejarlo así:
    @ElementCollection
    @CollectionTable(name = "movie_genres", joinColumns = @JoinColumn(name = "movie_id"))
    @Column(name = "genre")
    private List<String> genres;
}