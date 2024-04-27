package com.example.volunteerappbackend.Security;

import com.example.volunteerappbackend.DTOs.request.Auth.RefreshRequest;
import com.example.volunteerappbackend.DTOs.request.Auth.SignInRequest;
import com.example.volunteerappbackend.DTOs.request.Auth.SignUpRequest;
import com.example.volunteerappbackend.DTOs.response.JwtAuthenticationResponse;
import com.example.volunteerappbackend.Security.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody SignInRequest request) {
        return ResponseEntity.ok(authenticationService.signIn(request));
    }

    @PostMapping("/refresh/access")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshRequest request) {
        return ResponseEntity.ok(authenticationService.refreshToken(request));
    }

    @PostMapping("/register")
    public ResponseEntity<JwtAuthenticationResponse> signUp(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signUp(request));
    }
}
