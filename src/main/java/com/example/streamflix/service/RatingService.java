package com.example.streamflix.service;

import com.example.streamflix.exception.UnauthorizedRatingDeletionException;
import com.example.streamflix.model.Rating;
import com.example.streamflix.model.Movie;
import com.example.streamflix.model.User;
import com.example.streamflix.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

  @Autowired
  private RatingRepository ratingRepository;

  // Añadir valoración a una película
  public Rating addRating(Rating rating, User user, Movie movie) {
    rating.setUser(user);
    rating.setMovie(movie);
    return ratingRepository.save(rating);
  }

  // Obtener valoraciones de una película
  public List<Rating> getRatingsByMovie(Movie movie) {
    return ratingRepository.findByMovieId(movie.getId());
  }

  // Obtener valoraciones realizadas por un usuario
  public List<Rating> getRatingsByUser(User user) {
    return ratingRepository.findByUserId(user.getId());
  }

  // Eliminar valoración propia
  public void deleteOwnRating(Long ratingId, User user) {
    Rating rating = ratingRepository.findById(ratingId)
            .orElseThrow(() -> new IllegalArgumentException("Valoración no encontrada."));
    if (!rating.getUser().equals(user)) {
      throw new UnauthorizedRatingDeletionException("No puedes eliminar valoraciones de otros usuarios.");
    }
    ratingRepository.deleteById(ratingId);
  }
}