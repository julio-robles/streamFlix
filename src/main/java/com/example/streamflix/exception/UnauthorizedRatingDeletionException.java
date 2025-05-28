package com.example.streamflix.exception;

public class UnauthorizedRatingDeletionException extends RuntimeException {
    public UnauthorizedRatingDeletionException(String message) {
        super(message);
    }
}