package com.example.streamflix.rental.controller;

import com.example.streamflix.rental.dto.*;
import com.example.streamflix.rental.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.ParameterObject;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/rentals")
@RequiredArgsConstructor
public class RentalController {

    private final RentalService service;

    @PostMapping
    @Operation(summary = "Crear alquiler") @ApiResponse(responseCode = "201")
    public ResponseEntity<RentalResponseDto> create(@Valid @RequestBody RentalCreateDto dto) {
        RentalResponseDto created = service.create(dto);
        return ResponseEntity
                .created(URI.create("/api/v1/rentals/" + created.id()))
                .body(created);
    }

    @PatchMapping("{id}/finish")
    @Operation(summary = "Finalizar alquiler")
    public RentalResponseDto finish(@PathVariable UUID id) {
        return service.finish(id);
    }

    @GetMapping
    @Operation(summary = "Listar alquileres")
    public Page<RentalResponseDto> list(@RequestParam UUID userId,
                                        @ParameterObject Pageable pageable) {
        return service.list(userId, pageable);
    }
}
