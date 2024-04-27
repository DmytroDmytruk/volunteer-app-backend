package com.example.volunteerappbackend.entities;


import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "support_services")
public class SupportService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "support_service_id")
    private Long id;

    @Column(name = "service_name")
    private String serviceName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_category_id")
    private SupportServiceCategory category;

    @Column(name = "description")
    private String description;

    @Column(name = "reference")
    private String reference;

    // Getters and setters
}