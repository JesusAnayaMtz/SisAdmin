package com.proyect.ventas.controllers;

import com.proyect.ventas.dto.UserRequestDTO;
import com.proyect.ventas.dto.UserResponseDTO;
import com.proyect.ventas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    //Obtener todos los usuarios(solo admin)
    @GetMapping
    public List<UserResponseDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    //Crear un nuevo usuario
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO){
        return ResponseEntity.ok(userService.createUser(userRequestDTO));
    }

    //Actualizar Usuario
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody UserRequestDTO userRequestDTO){
        return ResponseEntity.ok(userService.updateUser(id, userRequestDTO));
    }

    //Eliminar Usuario
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok("Usuario Eliminado Correctamente");
    }
}
