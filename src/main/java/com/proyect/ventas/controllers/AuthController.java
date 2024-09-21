package com.proyect.ventas.controllers;


import com.proyect.ventas.config.JwtTokenProvider;
import com.proyect.ventas.dto.AuthRequestDTO;
import com.proyect.ventas.dto.AuthResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody AuthRequestDTO request) {
        try {
            // Realiza la autenticación
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            //Establece el contexto de autenticacion
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Obtiene el nombre del usuario autenticado
            String username = authentication.getName();

            //Obtenemos los roles del usuario
            List<String> roles = authentication.getAuthorities().stream()
                    .map(authority -> authority.getAuthority())
                    .collect(Collectors.toList());

            // Genera el token JWT con el nombre de usuario
            String token = jwtTokenProvider.generateToken(username);

            //devuelve el token y los roles en la respuesta
            return new AuthResponseDTO(token, username, roles);
        } catch (AuthenticationException e) {
            throw new RuntimeException("Credenciales inválidas");
        }
    }
}
