package com.example.streamflix.repository;

import com.example.streamflix.model.Genre;
import com.example.streamflix.model.Movie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    private Genre createGenre(String name) {
        Genre genre = new Genre();
        genre.setName(name);
        return genre;
    }

    @Test
    @DisplayName("Buscar por título ignorando mayúsculas/minúsculas")
    void testFindByTitleContainingIgnoreCase() {
        Genre sciFi = createGenre("Sci-Fi");
        Genre action = createGenre("Action");

        Movie movie = new Movie();
        movie.setTitle("Inception");
        movie.setGenres(List.of(sciFi, action));
        movie.setReleaseYear(2010);
        movie.setAgeRating("PG-13");
        movieRepository.save(movie);

        var result = movieRepository.findByTitleContainingIgnoreCase("inception", PageRequest.of(0, 10));
        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0).getTitle()).isEqualTo("Inception");
    }

    @Test
    @DisplayName("Buscar por año de estreno")
    void testFindByReleaseYear() {
        Genre action = createGenre("Action");

        Movie movie = new Movie();
        movie.setTitle("Matrix");
        movie.setGenres(List.of(action));
        movie.setReleaseYear(1999);
        movie.setAgeRating("R");
        movieRepository.save(movie);

        var result = movieRepository.findByReleaseYear(1999, PageRequest.of(0, 10));
        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0).getTitle()).isEqualTo("Matrix");
    }

    @Test
    @DisplayName("Buscar por clasificación de edad")
    void testFindByAgeRatingIgnoreCase() {
        Genre sciFi = createGenre("Sci-Fi");

        Movie movie = new Movie();
        movie.setTitle("Interstellar");
        movie.setGenres(List.of(sciFi));
        movie.setReleaseYear(2014);
        movie.setAgeRating("PG-13");
        movieRepository.save(movie);

        var result = movieRepository.findByAgeRatingIgnoreCase("pg-13", PageRequest.of(0, 10));
        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0).getTitle()).isEqualTo("Interstellar");
    }
}