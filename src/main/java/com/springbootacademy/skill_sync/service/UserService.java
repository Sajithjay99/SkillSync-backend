package com.springbootacademy.skill_sync.service;

import com.springbootacademy.skill_sync.dto.UserDTO;

public interface UserService {
    String registerUser(UserDTO userDTO);
    String login(String email, String password);
    UserDTO getUserProfileById(String id);
    String updateUserProfile(String id, UserDTO userDTO);
}
