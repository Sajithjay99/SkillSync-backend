package com.springbootacademy.skill_sync.service;

import com.example.pafbackend.models.User;
import com.example.pafbackend.models.UserProfile;
import com.example.pafbackend.repositories.UserRepository;
import com.example.pafbackend.repositories.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository userProfileRepository; // Assuming you have a UserProfile repository

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(userRequest);

        // Extract email from OAuth user info (assuming 'email' attribute is available)
        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");

        // Use email as the username for OAuth users
        String username = (email != null) ? email : name + "_" + userRequest.getClientRegistration().getRegistrationId();
