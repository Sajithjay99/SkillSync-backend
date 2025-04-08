package com.springbootacademy.skill_sync.service.imp;

import com.springbootacademy.skill_sync.dto.UserDTO;
import com.springbootacademy.skill_sync.entity.User;
import com.springbootacademy.skill_sync.repo.UserRepo;
import com.springbootacademy.skill_sync.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceIMPL implements UserService {

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String jwtSecret;

    private static final String DEFAULT_PROFILE_PIC = "https://img.freepik.com/free-vector/blue-circle-with-white-user_78370-4707.jpg?semt=ais_country_boost&w=740";

    @Autowired
    public UserServiceIMPL(UserRepo userRepo, BCryptPasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String registerUser(UserDTO dto) {
        if (userRepo.findByEmail(dto.getEmail()).isPresent()) {
            return "User already exists with this email.";
        }

        String hashedPassword = passwordEncoder.encode(dto.getPassword());

        String profilePic = (dto.getProfilePictureUrl() == null || dto.getProfilePictureUrl().isEmpty())
                ? DEFAULT_PROFILE_PIC
                : dto.getProfilePictureUrl();

        User user = new User(
                null,
                dto.getFirstName(),
                dto.getLastName(),
                dto.getEmail(),
                dto.getContactNumber(),
                hashedPassword,
                profilePic
        );

        userRepo.save(user);
        return "User registered successfully!";
    }

    @Override
    public String login(String email, String password) {
        Optional<User> userOpt = userRepo.findByEmail(email);

        if (userOpt.isPresent()) {
            User user = userOpt.get();

            if (passwordEncoder.matches(password, user.getPassword())) {
                return "Login successful! Token: " + generateToken(email);
            }
        }

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
    }

    @Override
    public UserDTO getUserProfileById(String id) {
        return userRepo.findById(id)
                .map(user -> new UserDTO(
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getContactNumber(),
                        null,
                        user.getProfilePictureUrl()
                ))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @Override
    public String updateUserProfile(String id, UserDTO dto) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setContactNumber(dto.getContactNumber());
        user.setProfilePictureUrl(
                (dto.getProfilePictureUrl() == null || dto.getProfilePictureUrl().isEmpty())
                        ? DEFAULT_PROFILE_PIC
                        : dto.getProfilePictureUrl()
        );

        userRepo.save(user);
        return "Profile updated successfully!";
    }

    private String generateToken(String email) {
        SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
