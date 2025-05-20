package com.example.streamflix.rental.mapper;

import com.example.streamflix.rental.domain.Rental;
import com.example.streamflix.rental.dto.RentalResponseDto;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-20T22:14:06+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.4 (Oracle Corporation)"
)
@Component
public class RentalMapperImpl implements RentalMapper {

    @Override
    public RentalResponseDto toDto(Rental entity) {
        if ( entity == null ) {
            return null;
        }

        UUID id = null;
        UUID userId = null;
        UUID movieId = null;
        String status = null;
        BigDecimal price = null;
        OffsetDateTime expiresAt = null;

        id = entity.getId();
        userId = entity.getUserId();
        movieId = entity.getMovieId();
        if ( entity.getStatus() != null ) {
            status = entity.getStatus().name();
        }
        price = entity.getPrice();
        expiresAt = entity.getExpiresAt();

        RentalResponseDto rentalResponseDto = new RentalResponseDto( id, userId, movieId, status, price, expiresAt );

        return rentalResponseDto;
    }
}
