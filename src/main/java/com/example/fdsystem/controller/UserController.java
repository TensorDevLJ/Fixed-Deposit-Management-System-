package com.example.fdsystem.controller;

import com.example.fdsystem.model.User;
import com.example.fdsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        // Very basic login for demo
        User existingUser = userService.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        if (existingUser.getPassword().equals(user.getPassword())) {
            return ResponseEntity.ok("Login Successful. User ID: " + existingUser.getId());
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}
