package com.brutal.dto;


import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private String rol;
    private String name;
}
