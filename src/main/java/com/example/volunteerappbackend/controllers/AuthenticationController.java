package com.example.volunteerappbackend.controllers;

import com.example.volunteerappbackend.DTOs.request.RefreshRequest;
import com.example.volunteerappbackend.DTOs.request.SignInRequest;
import com.example.volunteerappbackend.DTOs.response.JwtAuthenticationResponse;
import com.example.volunteerappbackend.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> signing(@RequestBody SignInRequest request) {
        return ResponseEntity.ok(authenticationService.signIn(request));
    }

    @PostMapping("/refresh/access")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshRequest request) {
        return ResponseEntity.ok(authenticationService.refreshToken(request));
    }

}
