package com.herd.API.models;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CowConfig {

    @Bean
    CommandLineRunner commandLineRunner(CowRepository repository) {
        return args -> {
            Cow cowA = new Cow(
                    "ec7c113d-a25d-45cc-9d60-604329e21d9b",
                    261,
                    88261,
                    "Healthy"
            );
            Cow cowB = new Cow(
                    "fe0a1ec1-e7d4-4b2d-a555-ae23926ee9d0",
                    222,
                    88222,
                    "Broken"
            );
            repository.saveAll(List.of(cowA, cowB));
        };
    }
}
