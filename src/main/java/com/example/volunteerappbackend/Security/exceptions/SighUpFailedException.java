package com.example.volunteerappbackend.Security.exceptions;

import org.springframework.security.core.AuthenticationException;

public class SighUpFailedException extends AuthenticationException {
    public SighUpFailedException(String message) {
        super("Registration failed, Reason: " + message);
    }
}
