package com.example.volunteerappbackend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "housing_advertisement")
public class HousingAdvertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "housing_advertisement_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "apartment_id", unique = true)
    private Apartment apartment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "adding_date")
    private Date addingDate;

    @Column(name = "is_active")
    private boolean isActive;

    // Getters and setters
}