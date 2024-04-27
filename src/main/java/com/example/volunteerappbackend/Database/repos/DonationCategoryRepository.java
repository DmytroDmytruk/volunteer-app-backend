package com.example.volunteerappbackend.Database.repos;


import com.example.volunteerappbackend.Database.entities.DonationCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationCategoryRepository extends JpaRepository<DonationCategory, Integer> {
}
