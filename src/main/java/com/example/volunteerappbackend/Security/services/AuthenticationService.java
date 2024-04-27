package com.example.volunteerappbackend.Security.services;


import com.example.volunteerappbackend.DTOs.request.Auth.RefreshRequest;
import com.example.volunteerappbackend.DTOs.request.Auth.SignInRequest;
import com.example.volunteerappbackend.DTOs.request.Auth.SignUpRequest;
import com.example.volunteerappbackend.DTOs.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signIn(SignInRequest request);
    JwtAuthenticationResponse signUp(SignUpRequest request);
    JwtAuthenticationResponse refreshToken(RefreshRequest request);
}
