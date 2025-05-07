package com.example.pafbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.pafbackend.models.UserProfile;
import com.example.pafbackend.repositories.UserProfileRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/userProfiles")
public class UserProfileController {

    @Autowired
    private UserProfileRepository userProfileRepository;

    // Create a new UserProfile
    @PostMapping
    public UserProfile createUserProfile(@RequestBody UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }

    // Retrieve all UserProfiles
    @GetMapping
    public List<UserProfile> getAllUserProfiles() {
        return userProfileRepository.findAll();
    }

    // Retrieve a UserProfile by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserProfile> getUserProfileById(@PathVariable String id) {
        Optional<UserProfile> userProfile = userProfileRepository.findById(id);
        return userProfile.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }