package com.example.volunteerappbackend.DTOs.mappers;


import com.example.volunteerappbackend.DTOs.request.Housing.AdvertisementRequest;
import com.example.volunteerappbackend.Database.entities.ApartmentPhoto;
import com.example.volunteerappbackend.Database.entities.HousingAdvertisement;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdvertisementMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public AdvertisementMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        //this.modelMapper.addConverter(toStringDate);
        //this.modelMapper.getTypeMap(String.class, LocalDate.class).setProvider(localDateProvider);
    }

    public HousingAdvertisement toEntity(AdvertisementRequest request) {
        List<ApartmentPhoto> photos = new ArrayList<>();
        HousingAdvertisement advertisement = modelMapper.map(request, HousingAdvertisement.class);
        for(byte[] photo : request.getPhotos()){
            photos.add(ApartmentPhoto.builder().photo(photo).build());
        }
        advertisement.setPhotos(photos);
        return advertisement;
    }
}
