package com.example.volunteerappbackend.DTOs.mappers;

import com.example.volunteerappbackend.DTOs.request.Donations.DonationRequest;
import com.example.volunteerappbackend.DTOs.response.Donations.DonationResponse;
import com.example.volunteerappbackend.Database.entities.DonationPhoto;
import com.example.volunteerappbackend.Database.entities.DonationsCollection;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class DonationMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public DonationMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DonationsCollection toEntity(DonationRequest request){
        List<DonationPhoto> photos = new ArrayList<>();
        for(String photo : request.getPhotos()){
            photos.add(DonationPhoto.builder()
                            .photo(photo.getBytes())
                            .build());
        }
        DonationsCollection donation = modelMapper.map(request, DonationsCollection.class);
        donation.setPhotos(photos);
        donation.setUploadDate(LocalDateTime.now());
        return donation;
    }

    public DonationResponse toResponse(DonationsCollection donationsCollection){
        List<String> donationPhotos = new ArrayList<>();
        for(DonationPhoto photo : donationsCollection.getPhotos()){
            donationPhotos.add(new String(photo.getPhoto()));
        }
        DonationResponse response = modelMapper.map(donationsCollection, DonationResponse.class);
        response.setPhotos(donationPhotos);
        response.setUploadDate(donationsCollection.getUploadDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return response;
    }
}
