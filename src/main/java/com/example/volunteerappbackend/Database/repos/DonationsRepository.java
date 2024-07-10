package com.example.volunteerappbackend.Database.repos;


import com.example.volunteerappbackend.Database.entities.DonationCategory;
import com.example.volunteerappbackend.Database.entities.DonationsCollection;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationsRepository extends JpaRepository<DonationsCollection, Integer> {
    List<DonationsCollection> findAllByDonationCategory(DonationCategory donationCategory);
}
