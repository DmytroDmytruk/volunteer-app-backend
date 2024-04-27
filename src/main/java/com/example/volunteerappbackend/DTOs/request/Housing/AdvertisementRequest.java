package com.example.volunteerappbackend.DTOs.request.Housing;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class AdvertisementRequest {
    private String type;
    private String title;
    private String address;
    private String roomsCount;
    private String price;
    private String area;
    private String peopleCount;
    private String peopleCountFree;
    private List<byte[]> photos;
}
