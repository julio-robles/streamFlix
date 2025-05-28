package com.example.streamflix.service;

import com.example.streamflix.model.Movie;
import com.example.streamflix.model.User;
import com.example.streamflix.model.Role;
import com.example.streamflix.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Movie createMovie(Movie movie, User user) {
        if (user.getRole() != Role.ADMIN) {
            throw new SecurityException("Only administrators can create movies.");
        }
        return movieRepository.save(movie);
    }
}