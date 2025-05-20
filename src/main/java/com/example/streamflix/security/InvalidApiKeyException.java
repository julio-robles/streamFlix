package com.example.streamflix.security;

public class InvalidApiKeyException extends RuntimeException {
    public InvalidApiKeyException() { super("Invalid API key"); }
}