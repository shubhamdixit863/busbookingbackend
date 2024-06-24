package com.example.backendbusbookingservice.service;

import com.example.backendbusbookingservice.dto.LoginRequest;
import com.example.backendbusbookingservice.dto.RoleRequest;
import com.example.backendbusbookingservice.dto.SignupRequest;
import com.example.backendbusbookingservice.entity.Roles;
import com.example.backendbusbookingservice.entity.Users;
import com.example.backendbusbookingservice.repository.RoleRepository;
import com.example.backendbusbookingservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;  // dependency injection

    @Autowired
    RoleRepository roleRepository;

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

    public Long SaveUser(SignupRequest signupRequest) {
        Users user = new Users();
        user.setUsername(signupRequest.getUsername());
        user.setPassword(signupRequest.getPassword());
        user.setName(signupRequest.getName());
        // find the role by role Id
        var role=roleRepository.findById(signupRequest.getRole());
        var roles=new HashSet<Roles>();
        roles.add(role.get());
        user.setRoles(roles);
        return userRepository.save(user).getId();
    }

    public Roles saveRole(RoleRequest roleRequest){
        Roles role=new Roles();
        role.setName(roleRequest.getName());
        return roleRepository.save(role);
    }
}
