package com.example.streamflix.rental;

import com.example.streamflix.rental.config.RentalPricingProperties;
import com.example.streamflix.rental.domain.*;
import com.example.streamflix.rental.dto.*;
import com.example.streamflix.rental.exception.*;
import com.example.streamflix.rental.mapper.RentalMapper;
import com.example.streamflix.rental.repository.RentalRepository;
import com.example.streamflix.rental.service.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class RentalServiceImpl implements RentalService {

    private final RentalRepository repository;
    private final RentalMapper mapper;
    private final RentalPricingProperties pricing;

    @Qualifier("stripe")
    private final PaymentGateway paymentGateway;

    @Override
    public RentalResponseDto create(RentalCreateDto dto) {

        if (repository.existsByUserIdAndMovieIdAndStatus(dto.userId(), dto.movieId(), RentalStatus.ACTIVE))
            throw new DuplicateRentalException();

        BigDecimal price = ("4K".equalsIgnoreCase(dto.resolution()) ? pricing.uhd() : pricing.hd());

        Rental rental = new Rental(
                null,
                dto.userId(),
                dto.movieId(),
                RentalStatus.PENDING,
                price,
                OffsetDateTime.now(),
                null
        );

        paymentGateway.charge(price, dto.userId());

        rental.setStatus(RentalStatus.ACTIVE);
        rental.setExpiresAt(OffsetDateTime.now().plusHours(48));

        return mapper.toDto(repository.save(rental));
    }

    @Override
    public RentalResponseDto finish(UUID rentalId) {

        Rental rental = repository.findById(rentalId)
                .orElseThrow(RentalNotFoundException::new);

        if (rental.getStatus() != RentalStatus.ACTIVE
                || rental.getExpiresAt().isBefore(OffsetDateTime.now()))
            throw new IllegalStateException("Cannot finish rental");

        rental.setStatus(RentalStatus.FINISHED);
        return mapper.toDto(rental);
    }

    @Override
    public Page<RentalResponseDto> list(UUID userId, Pageable pageable) {
        return repository.findByUserId(userId, pageable).map(mapper::toDto);
    }
}
