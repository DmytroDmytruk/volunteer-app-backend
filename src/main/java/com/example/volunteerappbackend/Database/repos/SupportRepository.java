package com.example.volunteerappbackend.Database.repos;


import com.example.volunteerappbackend.Database.entities.SupportService;
import com.example.volunteerappbackend.Database.entities.SupportServiceCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupportRepository extends JpaRepository<SupportService, Integer> {
    List<SupportService> findAllByCategory(SupportServiceCategory supportServiceCategory);
}
