package com.example.backendbusbookingservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@Entity
public class Users {
    @Id
    @GeneratedValue
    private Long id;
    private String  name;
    @Column(unique=true)
    private String username;
    private String password;
    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Roles> roles;






}
