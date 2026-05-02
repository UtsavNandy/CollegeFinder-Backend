package com.utsav.college.controller;

import com.utsav.college.model.User;
import com.utsav.college.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/register")
    public String register(@RequestBody User user) {

        if (repo.findByEmail(user.getEmail()).isPresent()) {
            return "User already exists";
        }

        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);

        return "User Registered Successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {

        User existing = repo.findByEmail(user.getEmail()).orElse(null);

        if (existing != null && encoder.matches(user.getPassword(), existing.getPassword())) {
            return "Login Success";
        }

        return "Invalid Credentials";
    }
}