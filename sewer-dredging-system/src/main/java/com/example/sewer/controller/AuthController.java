package com.example.sewer.controller;

import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    // For demonstration, a simple login endpoint
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");
        
        Map<String, Object> response = new HashMap<>();
        if (("admin".equals(username) && "admin123".equals(password)) ||
            ("accountant".equals(username) && "accountant123".equals(password))) {
            response.put("success", true);
            response.put("role", username.toUpperCase()); // ADMIN or ACCOUNTANT
        } else {
            response.put("success", false);
            response.put("message", "Invalid credentials");
        }
        return response;
    }
}
