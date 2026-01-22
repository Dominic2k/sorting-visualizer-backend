package com.sortingvisualizer.controller;

import com.sortingvisualizer.dto.AuthResponse;
import com.sortingvisualizer.dto.LoginRequest;
import com.sortingvisualizer.dto.RegisterRequest;
import com.sortingvisualizer.model.User;
import com.sortingvisualizer.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    private final AuthService authService;
    
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        AuthResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> getCurrentUser(@AuthenticationPrincipal User user) {
        Map<String, Object> response = new HashMap<>();
        response.put("email", user.getEmail());
        response.put("displayName", user.getDisplayName());
        response.put("createdAt", user.getCreatedAt());
        
        // Include avatarUrl if available (for OAuth users)
        if (user.getAvatarUrl() != null) {
            response.put("avatarUrl", user.getAvatarUrl());
        }
        
        return ResponseEntity.ok(response);
    }
}

