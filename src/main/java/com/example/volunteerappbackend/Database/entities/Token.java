package com.example.volunteerappbackend.Database.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String token;
    String refreshToken;
    Date expiresAt;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    Boolean isActive;
}
