package com.example.backendbusbookingservice.controllers;

import com.example.backendbusbookingservice.dto.LoginRequest;
import com.example.backendbusbookingservice.dto.RoleRequest;
import com.example.backendbusbookingservice.dto.SignupRequest;
import com.example.backendbusbookingservice.security.JwtUtil;
import com.example.backendbusbookingservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Map<String,Object>>  validateCredentials(@RequestBody LoginRequest loginRequest)
    {
        var userData=userService.GetUserByUsername(loginRequest);
        var data = new HashMap<String, Object>();

        var jwtUtil=new JwtUtil();
        String token=jwtUtil.generateToken(userData.getUsername(),userData.getRoles());
        data.put("status","Success" );
        data.put("token",token);
        return  ResponseEntity.ok().body(new HashMap<>(data));
    }

    @PostMapping("/signup")
    public Long addNewCredentials(@RequestBody SignupRequest signupRequest)
    {
        return userService.SaveUser(signupRequest);
    }

    @PostMapping("/role")
    public Long createRole(@RequestBody RoleRequest roleRequest)
    {
        return userService.saveRole(roleRequest).getId();
    }



}
