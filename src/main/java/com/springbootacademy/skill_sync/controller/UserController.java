package com.springbootacademy.skill_sync.controller;

import com.springbootacademy.skill_sync.dto.LoginRequestDTO;
import com.springbootacademy.skill_sync.dto.UserDTO;
import com.springbootacademy.skill_sync.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody UserDTO userDTO) {
        return userService.registerUser(userDTO);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO loginRequest) {
        return userService.login(loginRequest.getEmail(), loginRequest.getPassword());
    }

    @GetMapping("/profile/{id}")
    public UserDTO getProfile(@PathVariable String id) {
        return userService.getUserProfileById(id);
    }

    @PutMapping("/profile/{id}")
    public String updateProfile(@PathVariable String id, @RequestBody UserDTO userDTO) {
        return userService.updateUserProfile(id, userDTO);
    }
}
