package org.example.controller;

import org.example.model.AppUser;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class AuthController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AppUser user) {
        // Check if the username already exists
        if (userRepo.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("Username already exists!");
        }

        // Encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Assign default role
        user.setRole("ROLE_USER");

        // Save user to database
        userRepo.save(user);

        return ResponseEntity.ok("User registered successfully!");
    }
}
