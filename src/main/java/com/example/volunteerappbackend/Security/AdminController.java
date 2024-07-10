package com.example.volunteerappbackend.Security;

import com.example.volunteerappbackend.DTOs.response.Security.UserInfo;
import com.example.volunteerappbackend.Security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @PostMapping("/verify-info")
    public void VerifyUser(@RequestParam long id){
        userService.verifyUser(id);
    }

    @GetMapping("get-user-info")
    public ResponseEntity<UserInfo> GetUserInfo(@RequestParam long id){

    }
}
