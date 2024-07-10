package com.example.volunteerappbackend.DTOs.response.Security;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserInfo {    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String firstName;
    private String email;
    private String lastName;
    private String username;
    private String password;
    private String phoneNumber;
    private String country;
    private LocalDate birthDay;
    private boolean isVerified;
    private byte[] passportData;
}
