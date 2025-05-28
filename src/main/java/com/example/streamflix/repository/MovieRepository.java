package com.example.streamflix.repository;

import com.example.streamflix.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    // Buscar por título (contiene, ignorando mayúsculas/minúsculas)
    Page<Movie> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    // Buscar por género (la lista de géneros contiene el valor)
    Page<Movie> findByGenresIgnoreCase(String genre, Pageable pageable);

    // Buscar por año de estreno
    Page<Movie> findByReleaseYear(Integer releaseYear, Pageable pageable);

    // Buscar por título, género o año (cualquiera de los tres)
    Page<Movie> findByTitleContainingIgnoreCaseOrGenresIgnoreCaseOrReleaseYear(
            String title, String genre, Integer releaseYear, Pageable pageable
    );

    // Filtrar por género, año y clasificación
    Page<Movie> findByGenresIgnoreCaseAndReleaseYearAndAgeRatingIgnoreCase(
            String genre, Integer releaseYear, String ageRating, Pageable pageable
    );

    // Filtrar por género y año
    Page<Movie> findByGenresIgnoreCaseAndReleaseYear(
            String genre, Integer releaseYear, Pageable pageable
    );

    // Filtrar por género y clasificación
    Page<Movie> findByGenresIgnoreCaseAndAgeRatingIgnoreCase(
            String genre, String ageRating, Pageable pageable
    );

    // Filtrar por año y clasificación
    Page<Movie> findByReleaseYearAndAgeRatingIgnoreCase(
            Integer releaseYear, String ageRating, Pageable pageable
    );

    Page<Movie> findByAgeRatingIgnoreCase(String ageRating, Pageable pageable);

}