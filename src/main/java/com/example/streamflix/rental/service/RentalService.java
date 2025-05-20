package com.example.streamflix.rental.service;

import com.example.streamflix.rental.dto.*;
import org.springframework.data.domain.*;

import java.util.UUID;

public interface RentalService {

    RentalResponseDto create(RentalCreateDto dto);

    RentalResponseDto finish(UUID rentalId);

    Page<RentalResponseDto> list(UUID userId, Pageable pageable);
}
