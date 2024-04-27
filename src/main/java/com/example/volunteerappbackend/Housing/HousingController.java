package com.example.volunteerappbackend.Housing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HousingController {
    @Autowired
    private HousingService housingService;

    @GetMapping("/get-all-adverisments")
    public void getAllAdvertisments() {
        return housingService.getAllAds();
    }

    @GetMapping("/get-adverisments")
    public void getAllAdvertisments() {
        return housingService.getAllAds();
    }

    @GetMapping("/get-adverisment-info")
    public void getAdvertisment() {

    }

    @PostMapping("/add-adverisment")
    public void addAd(){
        housingService.addAdvertisment();
    }
}
