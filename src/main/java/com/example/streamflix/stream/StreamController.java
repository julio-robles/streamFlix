package com.example.streamflix.stream;

// REST controller /api/v1/stream, GET {movieId}, sólo ROLE_PREMIUM puede acceder (PreAuthorize)

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stream")
public class StreamController {

    @GetMapping("{movieId}")
    @PreAuthorize("hasRole('PREMIUM')")
    public String streamMovie(@PathVariable String movieId) {
        // simulación ― en real devolverías el contenido / token firmado
        return "Streaming movie " + movieId;
    }
}