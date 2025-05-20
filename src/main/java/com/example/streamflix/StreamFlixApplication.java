package com.example.streamflix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.example.streamflix.rental.config.RentalPricingProperties;

@SpringBootApplication
@EnableConfigurationProperties(RentalPricingProperties.class)
public class StreamFlixApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamFlixApplication.class, args);
    }
}