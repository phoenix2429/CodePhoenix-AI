package com.codephoenix.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String fullName;
    private String email;
    private String password;
    private String role; // "ADMIN", "ENGINEER", "MANAGER"
}
