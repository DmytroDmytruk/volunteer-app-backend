package com.example.volunteerappbackend.Database.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApartmentPhoto {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "housing_advertisement_id", nullable = false)
    private HousingAdvertisement housingAdvertisement;
    @Column
    @Lob
    private byte[] photo;
}
