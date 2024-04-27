package com.example.volunteerappbackend.Housing.services;


import com.example.volunteerappbackend.DTOs.mappers.AdvertisementMapper;
import com.example.volunteerappbackend.DTOs.request.Housing.AdvertisementRequest;
import com.example.volunteerappbackend.Database.entities.HousingAdvertisement;
import com.example.volunteerappbackend.Database.entities.User;
import com.example.volunteerappbackend.Database.repos.HousingRepository;
import com.example.volunteerappbackend.Database.repos.UserRepository;
import com.example.volunteerappbackend.Security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class HousingService {
    @Autowired
    private HousingRepository housingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdvertisementMapper advertisementMapper;
    @Autowired
    private UserService userService;

    public boolean addAdvertisment(AdvertisementRequest request, String token) {
        String CurrentUsername = userService.getUsername(token);
        try{
            User user = userRepository.findByUsername(CurrentUsername).orElse(null);
            HousingAdvertisement advertisement = advertisementMapper.toEntity(request);
            advertisement.setUser(user);
            housingRepository.save(advertisement);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    
    
    
    
    /*
    
    @Autowired
    ApsRepository apsRepository;
    @Autowired
    UserRepository userRepository;


    public List<AdsDTO> getAllAds() {
        List<AdsDTO> adsDTOList = new ArrayList<>();
        List<HousingAdvertisement> housingAdvertisements = housingRepository.findAll();
        for (HousingAdvertisement advertisement : housingAdvertisements){
            adsDTOList.add(convertToDTO(advertisement));
        }
        return adsDTOList;
    }

    public ApsDTO getApp(String adId) {
        return convertToDTO(apsRepository.findByHousingAdvertisement(housingRepository
                .findById(Long.parseLong(adId)).orElseThrow()));
    }


    public AdsDTO convertToDTO(HousingAdvertisement housingAdvertisement) {
        if (housingAdvertisement == null) {
            return null;
        }

        AdsDTO adsDTO = new AdsDTO();
        adsDTO.setId(housingAdvertisement.getId());
        adsDTO.setUserId(housingAdvertisement.getUser() != null ? housingAdvertisement.getUser().getUserId(): null);
        adsDTO.setApartmentId(housingAdvertisement.getApartment() != null ? housingAdvertisement.getApartment().getId() : null);
        adsDTO.setAddingDate(housingAdvertisement.getAddingDate());
        adsDTO.setActive(housingAdvertisement.isActive());
        return adsDTO;
    }

    public HousingAdvertisement convertToEntity(AdsDTO adsDTO) {
        if (adsDTO == null) {
            return null;
        }

        HousingAdvertisement housingAdvertisement = new HousingAdvertisement();
        housingAdvertisement.setId(adsDTO.getId());
        housingAdvertisement.setAddingDate(adsDTO.getAddingDate());
        housingAdvertisement.setActive(adsDTO.isActive());

        if (adsDTO.getUserId() != null) {
            User user = userRepository.findById(adsDTO.getUserId()).orElse(null);
            housingAdvertisement.setUser(user);
        }

        if (adsDTO.getApartmentId() != null) {
            Apartment apartment = apsRepository.findById(Math.toIntExact(adsDTO.getApartmentId())).orElse(null);
            housingAdvertisement.setApartment(apartment);
        }

        return housingAdvertisement;
    }


    public ApsDTO convertToDTO(Apartment apartment) {
        if (apartment == null) {
            return null;
        }

        ApsDTO apsDTO = new ApsDTO();
        apsDTO.setId(apartment.getId());
        apsDTO.setHousingAdvertisementId(apartment.getHousingAdvertisement() != null ? apartment.getHousingAdvertisement().getId() : null);
        apsDTO.setType(apartment.getType());
        apsDTO.setAddress(apartment.getAddress());
        apsDTO.setRoomsCount(apartment.getRoomsCount());
        apsDTO.setPrice(apartment.getPrice());
        apsDTO.setPeopleCount(apartment.getPeopleCount());
        apsDTO.setPeopleCountFree(apartment.getPeopleCountFree());

        return apsDTO;
    }



    public Apartment convertToEntity(ApsDTO apsDTO) {
        if (apsDTO == null) {
            return null;
        }

        Apartment apartment = new Apartment();
        apartment.setId(apsDTO.getId());
        var id = apsDTO.getId();
        HousingAdvertisement advertisement = housingRepository.findById(id).orElseThrow();
        apartment.setHousingAdvertisement(advertisement);
        apartment.setType(apsDTO.getType());
        apartment.setAddress(apsDTO.getAddress());
        apartment.setRoomsCount(apsDTO.getRoomsCount());
        apartment.setPrice(apsDTO.getPrice());
        apartment.setPeopleCount(apsDTO.getPeopleCount());
        apartment.setPeopleCountFree(apsDTO.getPeopleCountFree());

        return apartment;
    }

    public void addAd(AdsDTO ads) {
        housingRepository.save(convertToEntity(ads));
    }

    public void addAppartment(ApsDTO aps) {
        apsRepository.save(convertToEntity(aps));
    }*/
}
