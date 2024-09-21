package com.proyect.ventas.mapper;

import com.proyect.ventas.dto.UserRequestDTO;
import com.proyect.ventas.dto.UserResponseDTO;
import com.proyect.ventas.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    //Convertir de DTO a entidad
    public User toEntity(UserRequestDTO userRequestDTO){
        User user = new User();
        user.setName(userRequestDTO.getName());
        user.setLastname(userRequestDTO.getLastname());
        user.setUsername(userRequestDTO.getUsername());
        user.setPassword(userRequestDTO.getPassword());
        user.setRoles(userRequestDTO.getRole());
        return user;
    }

    //Convertir de entidad a DTO
    public UserResponseDTO toDTO(User user){
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setLastname(user.getLastname());
        userResponseDTO.setUsername(user.getUsername());
        userResponseDTO.setRole(user.getRoles());
        userResponseDTO.setCreatedAt(user.getCreatedAt());
        userResponseDTO.setUpdateAt(user.getUpdateAt());
        return userResponseDTO;
    }
}
