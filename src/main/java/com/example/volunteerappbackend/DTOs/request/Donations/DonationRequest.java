package com.example.volunteerappbackend.DTOs.request.Donations;


import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Validated
public class DonationRequest {
    @NotEmpty(message = "Donation name can`t be empty")
    private String name;
    @NotEmpty(message = "Description can`t be empty")
    private String description;
    @NotEmpty(message = "Reference can`t be empty")
    private String reference;
    @ValidateImageData
    List<String> photos;
    @ValidateDonationCategory
    private String donationCategory;
}