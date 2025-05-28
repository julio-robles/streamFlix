package com.example.streamflix.controller;

import com.example.streamflix.model.Movie;
import com.example.streamflix.model.User;
import com.example.streamflix.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie, @RequestParam User user) {
        try {
            Movie created = movieService.createMovie(movie, user);
            return ResponseEntity.ok(created);
        } catch (SecurityException e) {
            return ResponseEntity.status(403).build();
        }
    }

    @GetMapping
    public Page<Movie> getMovies(Pageable pageable) {
        return movieService.getMovies(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        Movie movie = movieService.findById(id);
        if (movie == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(
            @PathVariable Long id,
            @RequestBody Movie movieDetails,
            @RequestParam User user) {
        try {
            Movie updated = movieService.updateMovie(id, movieDetails, user);
            if (updated == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updated);
        } catch (SecurityException e) {
            return ResponseEntity.status(403).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id, @RequestParam User user) {
        try {
            boolean deleted = movieService.deleteMovie(id, user);
            if (!deleted) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.noContent().build();
        } catch (SecurityException e) {
            return ResponseEntity.status(403).build();
        }
    }

    @GetMapping("/search")
    public Page<Movie> searchMovies(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) Integer releaseYear,
            Pageable pageable) {
        return movieService.searchMovies(title, genre, releaseYear, pageable);
    }

    @GetMapping("/filter")
    public Page<Movie> filterMovies(
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) Integer releaseYear,
            @RequestParam(required = false) String ageRating,
            Pageable pageable) {
        return movieService.filterMovies(genre, releaseYear, ageRating, pageable);
    }


}