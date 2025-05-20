package com.example.streamflix.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class StubSecurityController {

    @GetMapping("/test/invalid-key")
    void throwInvalidKey() { throw new InvalidApiKeyException(); }

    @GetMapping("/test/rate-limit")
    void throwRateLimit() { throw new RateLimitExceededException(); }
}