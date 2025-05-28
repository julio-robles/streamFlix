package com.example.streamflix.controller;

import com.example.streamflix.model.Rating;
import com.example.streamflix.model.Movie;
import com.example.streamflix.model.User;
import com.example.streamflix.service.RatingService;
import com.example.streamflix.service.MovieService;
import com.example.streamflix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private UserService userService;

    // Añadir valoración a una película
    @PostMapping("/movie/{movieId}")
    public ResponseEntity<Rating> addRating(@PathVariable Long movieId, @RequestBody Rating rating, Principal principal) {
        User user = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));
        Movie movie = movieService.findById(movieId);
        Rating savedRating = ratingService.addRating(rating, user, movie);
        return ResponseEntity.ok(savedRating);
    }

    // Obtener valoraciones de una película
    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<Rating>> getRatingsByMovie(@PathVariable Long movieId) {
        Movie movie = movieService.findById(movieId);
        List<Rating> ratings = ratingService.getRatingsByMovie(movie);
        return ResponseEntity.ok(ratings);
    }

    // Obtener valoraciones realizadas por un usuario
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUser(@PathVariable Long userId) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));
        List<Rating> ratings = ratingService.getRatingsByUser(user);
        return ResponseEntity.ok(ratings);
    }

    // Eliminar valoración propia
    @DeleteMapping("/{ratingId}")
    public ResponseEntity<Void> deleteOwnRating(@PathVariable Long ratingId, Principal principal) {
        User user = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));
        ratingService.deleteOwnRating(ratingId, user);
        return ResponseEntity.noContent().build();
    }
}