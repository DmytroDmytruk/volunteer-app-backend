package com.example.volunteerappbackend.Security.services;


import com.example.volunteerappbackend.Database.entities.User;
import com.example.volunteerappbackend.Database.repos.UserRepository;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class UserService{
    private final UserRepository userRepository;
    private final JwtService jwtService;

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

    public void verifyUser(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        user.setVerified(true);
        userRepository.save(user);
    }
}
