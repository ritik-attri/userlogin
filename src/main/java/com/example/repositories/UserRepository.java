package com.example.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.models.UserProfile;

@Repository
public interface UserRepository extends JpaRepository<UserProfile, String> {
    UserProfile findByEmail(String email);
}

