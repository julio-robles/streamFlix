package com.example.streamflix.controller;

import com.example.streamflix.model.Movie;
import com.example.streamflix.model.User;
import com.example.streamflix.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


}