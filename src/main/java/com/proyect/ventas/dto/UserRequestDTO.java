package com.proyect.ventas.dto;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Data
public class UserRequestDTO {
    private String name;
    private String lastname;
    private String username;
    private String password;
    private String role;
    private LocalDate createdAt;

    @CreationTimestamp
    private LocalDate updateAt;
}
