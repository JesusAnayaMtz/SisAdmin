package com.proyect.ventas.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ClienteDTO {

    private Long id;
    private String nombre;
    private String rfc;
    private String email;
    private String telefono;
    private String celular;
    private String direccion;
    private LocalDate fechaCreacion;
    private LocalDate fechaActualizacion;
    private boolean activo;

}
