package com.example.volunteerappbackend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
public class TestController {

    @GetMapping("/hello")
    String hello() {
        return "Hello Admin";
    }
}
