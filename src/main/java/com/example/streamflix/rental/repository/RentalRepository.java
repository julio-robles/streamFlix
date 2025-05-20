package com.example.streamflix.rental.repository;

import com.example.streamflix.rental.domain.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RentalRepository extends JpaRepository<Rental, UUID> {

    boolean existsByUserIdAndMovieIdAndStatus(UUID userId, UUID movieId, RentalStatus status);

    Page<Rental> findByUserId(UUID userId, Pageable pageable);
}
