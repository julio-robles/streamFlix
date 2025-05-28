package com.example.streamflix.service;

import com.example.streamflix.exception.MovieNotFoundException;
import com.example.streamflix.model.Movie;
import com.example.streamflix.model.User;
import com.example.streamflix.model.Role;
import com.example.streamflix.repository.MovieRepository;
import com.example.streamflix.exception.UnauthorizedActionException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Movie createMovie(Movie movie, User user) {
        if (user.getRole() != Role.ADMIN) {
            throw new UnauthorizedActionException("Solo los administradores pueden crear películas.");
        }
        return movieRepository.save(movie);
    }

    public Page<Movie> getMovies(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    public Movie findById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Película no encontrada con id: " + id));
    }

    public Movie updateMovie(Long id, Movie movieDetails, User user) {
        if (user.getRole() != Role.ADMIN) {
            throw new UnauthorizedActionException("Solo los administradores pueden actualizar películas.");
        }
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Película no encontrada con id: " + id));
        movie.setTitle(movieDetails.getTitle());
        movie.setDescription(movieDetails.getDescription());
        movie.setReleaseYear(movieDetails.getReleaseYear());
        movie.setGenres(movieDetails.getGenres());
        movie.setAgeRating(movieDetails.getAgeRating());
        return movieRepository.save(movie);
    }

    public boolean deleteMovie(Long id, User user) {
        if (user.getRole() != Role.ADMIN) {
            throw new UnauthorizedActionException("Solo los administradores pueden eliminar películas.");
        }
        if (!movieRepository.existsById(id)) {
            throw new MovieNotFoundException("Película no encontrada con id: " + id);
        }
        movieRepository.deleteById(id);
        return true;
    }

    public Page<Movie> searchMovies(String title, String genre, Integer releaseYear, Pageable pageable) {
        return movieRepository.findByTitleContainingIgnoreCaseOrGenresIgnoreCaseOrReleaseYear(
                title != null ? title : "",
                genre != null ? genre : "",
                releaseYear,
                pageable
        );
    }

    public Page<Movie> filterMovies(String genre, Integer releaseYear, String ageRating, Pageable pageable) {
        if (genre != null && releaseYear != null && ageRating != null) {
            return movieRepository.findByGenresIgnoreCaseAndReleaseYearAndAgeRatingIgnoreCase(
                    genre, releaseYear, ageRating, pageable);
        } else if (genre != null && releaseYear != null) {
            return movieRepository.findByGenresIgnoreCaseAndReleaseYear(
                    genre, releaseYear, pageable);
        } else if (genre != null && ageRating != null) {
            return movieRepository.findByGenresIgnoreCaseAndAgeRatingIgnoreCase(
                    genre, ageRating, pageable);
        } else if (releaseYear != null && ageRating != null) {
            return movieRepository.findByReleaseYearAndAgeRatingIgnoreCase(
                    releaseYear, ageRating, pageable);
        } else if (genre != null) {
            return movieRepository.findByGenresIgnoreCase(genre, pageable);
        } else if (releaseYear != null) {
            return movieRepository.findByReleaseYear(releaseYear, pageable);
        } else if (ageRating != null) {
            return movieRepository.findByAgeRatingIgnoreCase(ageRating, pageable);
        } else {
            return movieRepository.findAll(pageable);
        }
    }

}