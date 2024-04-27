package com.example.volunteerappbackend.Security.services.impl;


import com.example.volunteerappbackend.DTOs.mappers.UserMapper;
import com.example.volunteerappbackend.DTOs.request.RefreshRequest;
import com.example.volunteerappbackend.DTOs.request.SignInRequest;
import com.example.volunteerappbackend.DTOs.request.SignUpRequest;
import com.example.volunteerappbackend.DTOs.response.JwtAuthenticationResponse;
import com.example.volunteerappbackend.Security.exceptions.RefreshTokenNotFoundException;
import com.example.volunteerappbackend.Security.services.AuthenticationService;
import com.example.volunteerappbackend.Security.services.JwtService;
import com.example.volunteerappbackend.entities.Token;
import com.example.volunteerappbackend.entities.User;
import com.example.volunteerappbackend.repos.TokenRepository;
import com.example.volunteerappbackend.repos.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final UserMapper userMapper;

    private final AuthenticationManager authenticationManager;

    @Value("${token.access.token.expiration}")
    private long accessTokenExpiration;

    @Transactional
    @Override
    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password."));
        List<Token> activeTokens = tokenRepository.findAllByUserAndIsActive(user, true).orElse(Collections.emptyList());
        if (!activeTokens.isEmpty()) {
            for (Token activeToken : activeTokens) {
                activeToken.setIsActive(false);
            }
            tokenRepository.saveAll(activeTokens);
        }
        return getJwtAuthenticationResponse(user);
    }

    @Override
    public JwtAuthenticationResponse signUp(SignUpRequest request) {
        Optional<User> user = userRepository.findByUsername(request.getUsername());
        if(user.isEmpty() && validateUser(request)) {
            User newUser = userRepository.save(userMapper.toEntity(request));
            return getJwtAuthenticationResponse(newUser);
        }
        return null;
    }

    @Override
    public JwtAuthenticationResponse refreshToken(RefreshRequest request) {
        Token token = tokenRepository.findByRefreshToken(request.getRefreshToken()).orElseThrow(() -> new RefreshTokenNotFoundException(request.getRefreshToken()));
        if (token.getExpiresAt().after(new Date()) && token.getIsActive()) {
            var user = token.getUser();
            var newAccessToken = jwtService.generateToken(user);
            var newRefreshToken = jwtService.generateRefreshToken(user);
            var expiresAt = new Date(System.currentTimeMillis() + accessTokenExpiration);
            token.setIsActive(false);
            tokenRepository.save(token);
            tokenRepository.save(new Token(newAccessToken, newRefreshToken, expiresAt, user, true));
            return new JwtAuthenticationResponse(newAccessToken, newRefreshToken, expiresAt);

        } else {
            tokenRepository.delete(token);
            return null;
        }
    }

    private boolean validateUser(SignUpRequest request) {
        return true;//Поки що так, а потім можливо знадобиться
    }

    private JwtAuthenticationResponse getJwtAuthenticationResponse(User newUser) {
        var jwt = jwtService.generateToken(newUser);
        var refreshToken = jwtService.generateRefreshToken(newUser);
        var expiresAt = new Date(System.currentTimeMillis() + accessTokenExpiration);
        tokenRepository.save(new Token(jwt, refreshToken, expiresAt, newUser, true));
        return JwtAuthenticationResponse.builder()
                .token(jwt)
                .refreshToken(refreshToken)
                .expiresAt(expiresAt)
                .build();
    }
}
