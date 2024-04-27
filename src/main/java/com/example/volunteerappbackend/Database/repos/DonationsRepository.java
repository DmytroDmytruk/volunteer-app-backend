package com.example.volunteerappbackend.Database.repos;



import com.example.volunteerappbackend.Database.entities.DonationsCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DonationsRepository extends JpaRepository<DonationsCollection, Integer> {
    @Transactional
    @Modifying
    @Query("UPDATE DonationsCollection dc SET dc.collectedAmount = :collectedAmount WHERE dc.id = :id")
    void updateCollectedAmountById(Long id, Float collectedAmount);
}
