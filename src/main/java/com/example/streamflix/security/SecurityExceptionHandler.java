package com.example.streamflix.security;

import com.example.streamflix.common.ErrorDto;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class SecurityExceptionHandler {

    /** 401 + WWW-Authenticate  */
    @ExceptionHandler(InvalidApiKeyException.class)
    public ResponseEntity<ErrorDto> invalidApiKey(InvalidApiKeyException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .header(HttpHeaders.WWW_AUTHENTICATE, "ApiKey")
                .body(new ErrorDto("unauthorized", ex.getMessage()));
    }

    /** 429 + Retry-After: 60 */
    @ExceptionHandler(RateLimitExceededException.class)
    public ResponseEntity<ErrorDto> rateLimit(RateLimitExceededException ex) {
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .header(HttpHeaders.RETRY_AFTER, "60")
                .body(new ErrorDto("too_many_requests", ex.getMessage()));
    }
}