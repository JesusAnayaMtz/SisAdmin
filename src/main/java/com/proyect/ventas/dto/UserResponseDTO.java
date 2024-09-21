package com.proyect.ventas.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserResponseDTO {

    private Long id;
    private String name;
    private String lastname;
    private String username;
    private String role;
    private LocalDate createdAt;
    private LocalDate updateAt;

}
