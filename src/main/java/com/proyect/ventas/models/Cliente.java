package com.proyect.ventas.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "clientes", uniqueConstraints = {@UniqueConstraint(columnNames = "rfc")})
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String rfc;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String telefono;

    @Column(nullable = false)
    private String celular;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    private boolean activo = true;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate fechaCreacion;

    @CreationTimestamp
    private LocalDate fechaActualizacion;
}
