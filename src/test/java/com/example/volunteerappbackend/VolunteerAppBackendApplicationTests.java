package com.example.volunteerappbackend;

import com.example.volunteerappbackend.DTOs.mappers.UserMapper;
import com.example.volunteerappbackend.DTOs.request.SignUpRequest;
import com.example.volunteerappbackend.entities.Role;
import com.example.volunteerappbackend.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Date;

@SpringBootTest
class VolunteerAppBackendApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Test
    void contextLoads() {
    }

    @Test
    void testUserMapper() {
        SignUpRequest request = SignUpRequest.builder()
                .firstName("John").lastName("Doe")
                .email("john.doe@example.com")
                .password("password")
                .username("User1")
                .isVerified("false")
                .role("VOLUNTEER")
                .birthDay("24-10-2004")
                .country("Ukraine")
                .passportData("Test Data 12345678".getBytes(StandardCharsets.UTF_8))
                .phoneNumber("+380933903051")
                .build();

        User user = User.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .password("password")
                .username("User1")
                .isVerified(false)
                .role(Role.VOLUNTEER)
                .birthDay(LocalDate.of(2004, 10, 24))
                .country("Ukraine")
                .passportData("Test Data 12345678".getBytes(StandardCharsets.UTF_8))
                .phoneNumber("+380933903051")
                .build();

        System.out.println(request.toString());
        System.out.println(user.toString());
        System.out.println(userMapper.toEntity(request).toString());

        Assertions.assertEquals(user.toString(), userMapper.toEntity(request).toString());
    }

}
