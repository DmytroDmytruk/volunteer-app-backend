package com.example.volunteerappbackend.Database.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "adding_date")
    private Date addingDate;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "type")
    private String type;

    @Column(name = "title")
    private String title;

    @Column(name = "address")
    private String address;

    @Column(name = "rooms_count")
    private Integer roomsCount;

    @Column(name = "price")
    private Integer price;

    @Column(name = "area")
    private Integer area;

    @Column(name = "people_count")
    private Integer peopleCount;

    @Column(name = "people_count_free")
    private Integer peopleCountFree;

    @OneToMany(mappedBy = "housingAdvertisement", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ApartmentPhoto> photos;

    // Getters and setters
}