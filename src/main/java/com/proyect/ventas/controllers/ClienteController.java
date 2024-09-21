package com.proyect.ventas.controllers;

import com.proyect.ventas.dto.ClienteDTO;
import com.proyect.ventas.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<ClienteDTO> obtenerCLientesActivos(){
        return clienteService.obtenerClientesActivos();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ClienteDTO crearCliente(@RequestBody ClienteDTO clienteDTO){
        return clienteService.crearCliente(clienteDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ClienteDTO actualizarCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO){
        return clienteService.actualizaCliente(id, clienteDTO);
    }

    //Desactivar un cliente
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivarCliente(@PathVariable Long id){
        clienteService.desactivarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
