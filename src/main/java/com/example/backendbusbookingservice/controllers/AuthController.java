package com.example.backendbusbookingservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

    @GetMapping("/")
    public Map<String,Object> validateCredentials()
    {
        var data = new HashMap<String, Object>();
        data.put("status","Success");
        return data;
    }


}
