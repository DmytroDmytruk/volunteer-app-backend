package com.example.volunteerappbackend.Database.entities;

import jakarta.persistence.*;
import lombok.*;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "donation_category")
public class DonationCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "donation_category_id")
    private Integer donationCategoryId;

    @Column(name = "category_name")
    private String categoryName;
}
