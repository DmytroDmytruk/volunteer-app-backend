package com.example.volunteerappbackend.Security.services;


import com.example.volunteerappbackend.DTOs.mappers.UserMapper;
import com.example.volunteerappbackend.DTOs.request.Auth.RefreshRequest;
import com.example.volunteerappbackend.DTOs.request.Auth.SignInRequest;
import com.example.volunteerappbackend.DTOs.request.Auth.SignUpRequest;
import com.example.volunteerappbackend.DTOs.response.Security.JwtAuthenticationResponse;
import com.example.volunteerappbackend.Database.entities.Token;
import com.example.volunteerappbackend.Database.entities.User;
import com.example.volunteerappbackend.Database.repos.TokenRepository;
import com.example.volunteerappbackend.Database.repos.UserRepository;
import com.example.volunteerappbackend.Security.exceptions.RefreshTokenNotFoundException;
import com.example.volunteerappbackend.Security.exceptions.SignInFailedException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService{
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final UserMapper userMapper;

    private final AuthenticationManager authenticationManager;

    @Value("${token.access.token.expiration}")
    private long accessTokenExpiration;

    @Transactional
    public JwtAuthenticationResponse signIn(SignInRequest request) {
        try{
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
        } catch (Exception e){
            throw new SignInFailedException("Invalid username or password.");
        }

    }

    public JwtAuthenticationResponse signUp(SignUpRequest request) {
        Optional<User> user = userRepository.findByUsername(request.getUsername());
        if(user.isPresent()) {
            User newUser = userRepository.save(userMapper.SignUpRequestToEntity(request));
            return getJwtAuthenticationResponse(newUser);
        }
        return null;
    }

    public JwtAuthenticationResponse refreshToken(RefreshRequest request) {
        Token token = tokenRepository.findByRefreshToken(request.getRefreshToken()).orElseThrow(() -> new RefreshTokenNotFoundException(request.getRefreshToken()));
        if (token.getExpiresAt().after(new Date()) && token.getIsActive()) {
            var user = token.getUser();
            var newAccessToken = jwtService.generateToken(user);
            var newRefreshToken = jwtService.generateRefreshToken(user);
            var expiresAt = new Date(System.currentTimeMillis() + accessTokenExpiration);
            token.setIsActive(false);
            tokenRepository.save(token);
            tokenRepository.save(Token.builder()
                    .token(newAccessToken)
                    .refreshToken(newRefreshToken)
                    .expiresAt(expiresAt)
                    .user(user)
                    .isActive(true)
                    .build());
            return new JwtAuthenticationResponse(newAccessToken, newRefreshToken, expiresAt);

        } else {
            tokenRepository.delete(token);
            return null;
        }
    }


    private JwtAuthenticationResponse getJwtAuthenticationResponse(User newUser) {
        var jwt = jwtService.generateToken(newUser);
        var refreshToken = jwtService.generateRefreshToken(newUser);
        var expiresAt = new Date(System.currentTimeMillis() + accessTokenExpiration);
        tokenRepository.save(Token.builder()
                .token(jwt)
                .refreshToken(refreshToken)
                .expiresAt(expiresAt)
                .user(newUser)
                .isActive(true)
                .build());
        return JwtAuthenticationResponse.builder()
                .token(jwt)
                .refreshToken(refreshToken)
                .expiresAt(expiresAt)
                .build();
    }
}
