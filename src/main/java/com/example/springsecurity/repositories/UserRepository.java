package com.example.springsecurity.repositories;

import com.example.springsecurity.Entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long>{
    public AppUser findByUsername(String username);
}
