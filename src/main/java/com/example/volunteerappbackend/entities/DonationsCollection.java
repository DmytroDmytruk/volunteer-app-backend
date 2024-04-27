package com.example.volunteerappbackend.entities;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "donations_collection")
public class DonationsCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "collection_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "reference")
    private String reference;

    @Column(name = "goal")
    private Float goal;

    @Column(name = "currency")
    private String currency;

    @Column(name = "collected_amount")
    private Float collectedAmount;

    @Column(name = "photo")
    private byte[] photo;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private DonationCategory donationCategory;
}