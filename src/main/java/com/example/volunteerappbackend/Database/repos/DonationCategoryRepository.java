package com.example.volunteerappbackend.Database.repos;


import com.example.volunteerappbackend.Database.entities.DonationCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DonationCategoryRepository extends JpaRepository<DonationCategory, Integer> {
    Optional<DonationCategory> findByCategoryName(String categoryName);
    Boolean existsByCategoryName(String categoryName);
}
