package com.example.streamflix.rental.exception;

public class RentalNotFoundException extends RuntimeException {
    public RentalNotFoundException() { super("Rental not found"); }
}
