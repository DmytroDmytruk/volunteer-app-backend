package com.example.volunteerappbackend.Database.repos;


import com.example.volunteerappbackend.Database.entities.HousingAdvertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HousingRepository extends JpaRepository<HousingAdvertisement, Long> {}
