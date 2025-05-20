package com.example.streamflix.rental.mapper;

import com.example.streamflix.rental.domain.Rental;
import com.example.streamflix.rental.dto.RentalResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface RentalMapper {
    RentalResponseDto toDto(Rental entity);
}
