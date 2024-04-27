package com.example.volunteerappbackend.Housing;

import com.example.volunteerappbackend.DTOs.request.Housing.AdvertisementRequest;
import com.example.volunteerappbackend.Housing.services.HousingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
public class HousingController {
    @Autowired
    private HousingService housingService;

    @GetMapping("/get-all-adverisements")
    public void getAllAdvertisments() {
        //return housingService.getAllAds();
    }

    @GetMapping("/get-adverisements")
    public void getAllAdvertisements() {
        //return housingService.getAllAds();
    }

    @GetMapping("/get-adverisement-info")
    public void getAdvertisment() {

    }

    @PostMapping("/add-adverisement")
    public void addAdverisment(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String token,
                               @RequestBody AdvertisementRequest request){
        housingService.addAdvertisment(request, token);
    }
}
