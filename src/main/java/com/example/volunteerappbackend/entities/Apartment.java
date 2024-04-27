package com.example.volunteerappbackend.entities;

import jakarta.persistence.*;
import lombok.*;

@Builder

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "apartments")
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apartment_id")
    private Long id;

    @OneToOne(mappedBy = "apartment")
    private HousingAdvertisement housingAdvertisement;

    @Column(name = "type")
    private String type;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "rooms_count")
    private Integer roomsCount;

    @Column(name = "price")
    private Integer price;

    @Column(name = "people_count")
    private Integer peopleCount;

    @Column(name = "people_count_free")
    private Integer peopleCountFree;

    // Getters and setters
}