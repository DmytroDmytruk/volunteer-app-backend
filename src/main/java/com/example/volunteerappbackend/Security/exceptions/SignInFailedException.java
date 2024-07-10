package com.example.volunteerappbackend.Security.exceptions;

import org.springframework.security.core.AuthenticationException;

public class SignInFailedException extends AuthenticationException {
    public SignInFailedException(String message) {
        super("Login failed, Reason: " + message);
    }
}
