package com.example.volunteerappbackend.config;

import com.example.volunteerappbackend.entities.Role;
import com.example.volunteerappbackend.entities.User;
import com.example.volunteerappbackend.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*
@Configuration
@RequiredArgsConstructor
public class DatabaseConfig {
    private final UserRepository userRepository;

    @Bean
    public CommandLineRunner init() {
        return args -> {
            userRepository.save(new User("Tolga", "Caglayan", "admin", "$2a$12$qr2Wa5upWw2/EjJP05s85.jDXE2N9nJXjBbKKwucoHHzf5Y041sVm", Role.ADMIN));
        };
    }
}*/
