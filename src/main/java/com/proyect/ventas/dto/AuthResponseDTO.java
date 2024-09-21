package com.proyect.ventas.dto;

import lombok.Data;

import java.util.List;

@Data
public class AuthResponseDTO {
    private String token;
    private String username;
    private List<String> roles;

    public AuthResponseDTO(String token, String username, List<String> roles){
        this.token = token;
        this.username = username;
        this.roles = roles;
    }
}
