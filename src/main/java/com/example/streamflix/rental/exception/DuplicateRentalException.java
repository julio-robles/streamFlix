package com.example.streamflix.rental.exception;

public class DuplicateRentalException extends RuntimeException {
    public DuplicateRentalException() { super("Rental already exists for this user and movie"); }
}