package com.springbootacademy.skill_sync.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootacademy.skill_sync.entity.UserConnection;
import com.springbootacademy.skill_sync.repo.UserConnectionRepository;

@RestController
@RequestMapping("/api/userConnections")
public class UserConnectionController {

    private final UserConnectionRepository userConnectionRepository;

    @Autowired
    public UserConnectionController(UserConnectionRepository userConnectionRepository) {
        this.userConnectionRepository = userConnectionRepository;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserConnection> getUserConnections(@PathVariable String userId) {
        UserConnection userConnection = userConnectionRepository.findByUserId(userId);
        if (userConnection != null) {
            return new ResponseEntity<>(userConnection, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<UserConnection> createUserConnection(@RequestBody UserConnection userConnection) {
        // Check if a document with the userId already exists
        UserConnection existingConnection = userConnectionRepository.findByUserId(userConnection.getUserId());
        if (existingConnection != null) {
            // Update the existing document by adding new friendIds
            List<String> currentFriendIds = existingConnection.getFriendIds();
            List<String> newFriendIds = userConnection.getFriendIds();
            currentFriendIds.addAll(newFriendIds);
            existingConnection.setFriendIds(currentFriendIds);
            UserConnection updatedConnection = userConnectionRepository.save(existingConnection);
            return new ResponseEntity<>(updatedConnection, HttpStatus.OK);
        } else {
            // No existing document, create a new one
            UserConnection savedUserConnection = userConnectionRepository.save(userConnection);
            return new ResponseEntity<>(savedUserConnection, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/{userId}/friends/{friendId}")
    public ResponseEntity<Void> unfriend(@PathVariable String userId, @PathVariable String friendId) {
        // Check if a document with the userId exists
        UserConnection existingConnection = userConnectionRepository.findByUserId(userId);
        if (existingConnection != null) {
            // Remove the friendId from the list of friendIds
            List<String> currentFriendIds = existingConnection.getFriendIds();
            currentFriendIds.remove(friendId);
            existingConnection.setFriendIds(currentFriendIds);
            userConnectionRepository.save(existingConnection);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            // No existing document, return 404
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}

