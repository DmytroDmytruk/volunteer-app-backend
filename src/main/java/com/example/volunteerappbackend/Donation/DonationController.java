package com.example.volunteerappbackend.Donation;

import com.example.volunteerappbackend.DTOs.request.Donations.DonationRequest;
import com.example.volunteerappbackend.DTOs.response.Donations.DonationResponse;
import com.example.volunteerappbackend.Donation.services.DonationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController("/donations")
public class DonationController {

    @Autowired
    private DonationsService donationsService;

    @PostMapping("/add-donation")
    public ResponseEntity<String> addDonation(@RequestBody @Valid DonationRequest request){
        donationsService.addDonation(request);
        return new ResponseEntity<>("donation is added", HttpStatus.OK);
    }

    @GetMapping("/find-all")
    public ResponseEntity<?> findAllDonations(@RequestParam(required = false) boolean idsOnly){
        if (idsOnly) {
            List<Long> donationIds = donationsService.findAllDonationIds();
            return new ResponseEntity<>(donationIds, HttpStatus.OK);
        } else {
            List<DonationResponse> donations = donationsService.findAllDonations();
            return new ResponseEntity<>(donations, HttpStatus.OK);
        }
    }

    @GetMapping("/get-donation-by-id")
    public ResponseEntity<?> getDonationById(@RequestParam int id){
        DonationResponse response = donationsService.getDonationById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete-donation")
    public ResponseEntity<String> deleteDonation(@RequestParam int id){
        donationsService.deleteDonation(id);
        return new ResponseEntity<>("donation is deleted", HttpStatus.OK);
    }


    @GetMapping("/get-by-category")
    public ResponseEntity<?> getByCategory(@RequestParam String category, @RequestParam(required = false) boolean idsOnly){
        if(idsOnly){
            List<Long> donationIds = donationsService.getIdsByCategory(category);
            return new ResponseEntity<>(donationIds, HttpStatus.OK);
        }
        List<DonationResponse> donations = donationsService.getByCategory(category);
        return new ResponseEntity<>(donations, HttpStatus.OK);
    }


}
