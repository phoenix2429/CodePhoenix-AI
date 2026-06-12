package com.codephoenix.service;

import com.codephoenix.dto.LoginRequest;
import com.codephoenix.dto.LoginResponse;
import com.codephoenix.dto.RegisterRequest;

public interface AuthService {

    LoginResponse login(LoginRequest request);

    LoginResponse register(RegisterRequest request);

}