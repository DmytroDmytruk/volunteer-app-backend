package com.example.volunteerappbackend.services;


import com.example.volunteerappbackend.DTOs.request.RefreshRequest;
import com.example.volunteerappbackend.DTOs.request.SignInRequest;
import com.example.volunteerappbackend.DTOs.request.SignUpRequest;
import com.example.volunteerappbackend.DTOs.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signIn(SignInRequest request);
    JwtAuthenticationResponse signUp(SignUpRequest request);
    JwtAuthenticationResponse refreshToken(RefreshRequest request);
}
