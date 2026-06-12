package com.codephoenix.controller;

import com.codephoenix.dto.LoginRequest;
import com.codephoenix.dto.LoginResponse;
import com.codephoenix.dto.RegisterRequest;
import com.codephoenix.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request) {

        return authService.login(request);
    }

    @PostMapping("/register")
    public LoginResponse register(
            @RequestBody RegisterRequest request) {

        return authService.register(request);
    }
}