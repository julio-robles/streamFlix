package com.example.streamflix.controller;

import com.example.streamflix.model.Genre;
import com.example.streamflix.model.Movie;
import com.example.streamflix.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MovieControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MovieRepository movieRepository;

    @BeforeEach
    void setUp() {
        movieRepository.deleteAll();
        Genre sciFi = new Genre();
        sciFi.setName("Sci-Fi");
        Movie movie = new Movie();
        movie.setTitle("Inception");
        movie.setGenres(List.of(sciFi));
        movieRepository.save(movie);
    }

    @Test
    void getAllMovies_returnsMovies() throws Exception {
        mockMvc.perform(get("/movies")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Inception"))
                .andExpect(jsonPath("$[0].genres[0].name").value("Sci-Fi"));
    }
}