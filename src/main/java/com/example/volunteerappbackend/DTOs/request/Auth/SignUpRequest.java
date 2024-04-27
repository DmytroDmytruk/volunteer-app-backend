package com.example.volunteerappbackend.DTOs.request.Auth;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String role;
    private String phoneNumber;
    private String country;
    private String birthDay;
    private String isVerified;

    private byte[] passportData;
    private byte[] soldierTicket;
    private byte[] volunteerTicket;
    private byte[] refugeeTicket;
}
