package com.example.backendbusbookingservice.repository;

import com.example.backendbusbookingservice.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Roles,Long> {
}
