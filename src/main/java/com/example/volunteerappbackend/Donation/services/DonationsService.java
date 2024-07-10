package com.example.volunteerappbackend.Donation.services;

import com.example.volunteerappbackend.DTOs.mappers.DonationMapper;
import com.example.volunteerappbackend.DTOs.request.Donations.DonationRequest;
import com.example.volunteerappbackend.DTOs.response.Donations.DonationResponse;
import com.example.volunteerappbackend.Database.entities.DonationCategory;
import com.example.volunteerappbackend.Database.entities.DonationsCollection;
import com.example.volunteerappbackend.Database.repos.DonationCategoryRepository;
import com.example.volunteerappbackend.Database.repos.DonationsRepository;
import com.example.volunteerappbackend.Donation.exceptions.AddDonationException;
import com.example.volunteerappbackend.Donation.exceptions.DeleteDonationException;
import com.example.volunteerappbackend.Donation.exceptions.ReceiveDonationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class DonationsService {
    @Autowired
    private DonationMapper mapper;

    @Autowired
    private DonationsRepository donationsRepository;
    @Autowired
    private DonationCategoryRepository donationCategoryRepository;

    public void addDonation(DonationRequest request) {
        try{
            DonationsCollection donationsCollection = mapper.toEntity(request);
            donationsRepository.save(donationsCollection);
        } catch (Exception e){
            throw new AddDonationException("There was an error adding donation : " + e.getMessage());
        }

    }


    public List<Long> findAllDonationIds() {
        try{
            return donationsRepository.findAll().stream()
                    .map(DonationsCollection::getId)
                    .collect(Collectors.toList());
        }catch (Exception e){
            throw new ReceiveDonationException("There was an error finding all donations ids : " + e.getMessage());
        }
    }

    public List<DonationResponse> findAllDonations() {
        try{
            return donationsRepository.findAll().stream()
                    .map(donationsCollection -> mapper.toResponse(donationsCollection))
                    .collect(Collectors.toList());
        }catch (Exception e){
            throw new ReceiveDonationException("There was an error finding all donations : " + e.getMessage());
        }


    }

    public DonationResponse getDonationById(int id) {
        DonationsCollection donationsCollection = donationsRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("donation with this id doesn`t exist"));
        return mapper.toResponse(donationsCollection);
    }

    public void deleteDonation(int id) {
        try{
            donationsRepository.deleteById(id);
        } catch (Exception e){
            throw new DeleteDonationException("There was an error deleting donation : " + e.getMessage());
        }
    }

    public List<Long> getIdsByCategory(String category) {
        DonationCategory donationCategory = donationCategoryRepository.findByCategoryName(category).orElseThrow(
                () -> new NoSuchElementException("donation Category doesn`t exist"));
        try{
            return donationsRepository.findAllByDonationCategory(donationCategory).stream()
                    .map(DonationsCollection::getId)
                    .collect(Collectors.toList());
        } catch (Exception e){
            throw new ReceiveDonationException("There was an error finding all donations ids by category : " + e.getMessage());
        }

    }

    public List<DonationResponse> getByCategory(String category) {
        DonationCategory donationCategory = donationCategoryRepository.findByCategoryName(category).orElseThrow(
                () -> new NoSuchElementException("donation Category doesn`t exist")
        );
        try {
            return donationsRepository.findAllByDonationCategory(donationCategory).stream()
                    .map(donationsCollection -> mapper.toResponse(donationsCollection))
                    .collect(Collectors.toList());

        } catch (Exception e){
            throw new ReceiveDonationException("There was an error finding all donations by category : " + e.getMessage());
        }
    }
}
