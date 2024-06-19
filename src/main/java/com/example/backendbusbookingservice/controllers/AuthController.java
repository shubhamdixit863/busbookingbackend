package com.example.backendbusbookingservice.controllers;

import com.example.backendbusbookingservice.dto.LoginRequest;
import com.example.backendbusbookingservice.dto.SignupRequest;
import com.example.backendbusbookingservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Map<String,Object> validateCredentials(@RequestBody LoginRequest loginRequest)
    {
        var userData=userService.GetUserByUsername(loginRequest);
        var data = new HashMap<String, Object>();
        data.put("status","Success" );
        data.put("user",userData);
        return data;
    }

    @PostMapping("/signup")
    public Long addNewCredentials(@RequestBody SignupRequest signupRequest)
    {
        return userService.SaveUser(signupRequest);
    }


}
