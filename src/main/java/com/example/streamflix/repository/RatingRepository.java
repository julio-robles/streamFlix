package com.example.streamflix.repository;

import com.example.streamflix.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByMovieId(Integer movieId);
    List<Rating> findByUserId(Integer userId);
    boolean existsByUserIdAndMovieId(Integer userId, Integer movieId);
}