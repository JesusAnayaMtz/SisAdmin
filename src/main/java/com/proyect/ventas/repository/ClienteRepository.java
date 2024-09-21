package com.proyect.ventas.repository;

import com.proyect.ventas.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    boolean existsByRfc(String rfc);

    List<Cliente> findByActivoTrue();
}
