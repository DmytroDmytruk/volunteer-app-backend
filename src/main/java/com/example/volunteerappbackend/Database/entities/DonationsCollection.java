package com.example.volunteerappbackend.Database.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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

    @Column(name = "date")
    private LocalDateTime uploadDate;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "reference")
    private String reference;

    @OneToMany(mappedBy = "donationCollection", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DonationPhoto> photos;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private DonationCategory donationCategory;
}