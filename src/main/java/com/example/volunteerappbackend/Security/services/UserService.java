package com.example.volunteerappbackend.Security.services;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService userDetailService();
    String getUsername(String token);
}
