package com.example.volunteerappbackend.Security.services.impl;


import com.example.volunteerappbackend.Security.services.JwtService;
import com.example.volunteerappbackend.Security.services.UserService;
import com.example.volunteerappbackend.Database.repos.UserRepository;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public UserDetailsService userDetailService() {
        return username -> userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }

    public String getUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(jwtService.getSecretKey()
                        .getBytes(StandardCharsets.UTF_8))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
