package com.example.volunteerappbackend.DTOs.response.Donations;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class DonationResponse {
    private String name;
    private String description;
    private String reference;
    private String uploadDate;
    List<String> photos;
    private String donationCategory;
}


