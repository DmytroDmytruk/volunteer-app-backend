package com.example.volunteerappbackend.Database.repos;


import com.example.volunteerappbackend.Database.entities.SupportServiceCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportCategoryRepository extends JpaRepository<SupportServiceCategory, Integer> {
}
