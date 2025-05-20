package com.example.streamflix.security;

public class RateLimitExceededException extends RuntimeException {
    public RateLimitExceededException() { super("Rate limit exceeded"); }
}
