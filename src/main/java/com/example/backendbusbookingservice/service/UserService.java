package com.example.backendbusbookingservice.service;

import com.example.backendbusbookingservice.dto.LoginRequest;
import com.example.backendbusbookingservice.dto.SignupRequest;
import com.example.backendbusbookingservice.entity.Users;
import com.example.backendbusbookingservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;  // dependency injection

    public Users GetUserByUsername(LoginRequest loginRequest) {
       var user= userRepository.findByUsername(loginRequest.getUsername());
       // we will check for the password
        if (user==null){
            return null;
        }
        if (user.getPassword().equals(loginRequest.getPassword())){
            return user;
        }

        return null;

    }

    public Long SaveUser(SignupRequest loginRequest) {
        Users user = new Users();
        user.setUsername(loginRequest.getUsername());
        user.setPassword(loginRequest.getPassword());
        return userRepository.save(user).getId();
    }
}
