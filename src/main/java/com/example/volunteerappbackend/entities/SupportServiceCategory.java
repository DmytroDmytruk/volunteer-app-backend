package com.example.volunteerappbackend.entities;


import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "support_service_categories")
public class SupportServiceCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_category_id")
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    // Getters and setters
}