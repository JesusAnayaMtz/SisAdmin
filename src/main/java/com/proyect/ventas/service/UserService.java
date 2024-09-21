package com.proyect.ventas.service;

import com.proyect.ventas.dto.UserRequestDTO;
import com.proyect.ventas.dto.UserResponseDTO;
import com.proyect.ventas.mapper.UserMapper;
import com.proyect.ventas.models.User;
import com.proyect.ventas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    //Obtener Todos Los Usuarios
    public List<UserResponseDTO> getAllUsers(){
        return userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    //Crear Un Nuevo Usuario
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO){
        if(userRepository.findByUsername(userRequestDTO.getUsername()).isPresent()){
            throw new RuntimeException("El Nombre De Usuario Ya Existe");
        }

        //Mapear DTO a Entidad
        User user = userMapper.toEntity(userRequestDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));


        userRepository.save(user);

        //Mapear entidad a DTO
        return userMapper.toDTO(user);
    }

    //Actualizar Un Usuario Existente
    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO){
        //Validamos Que El Id Del Usuario Exista
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario No Encontrado"));

        //pasamos los datos que viene como parametro al usuario codificando de nueco el password y validando que no este vacio
        user.setName(userRequestDTO.getName());
        user.setLastname(userRequestDTO.getLastname());
        user.setUsername(userRequestDTO.getUsername());
        if (!userRequestDTO.getPassword().isEmpty()){
            user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        }
        user.setRoles(userRequestDTO.getRole());
        user.setUpdateAt(LocalDate.now());

        //guardarmos ek usuario
        userRepository.save(user);

        //Mapeamos la entidad a DTO
        return userMapper.toDTO(user);
    }

    //Eliminar un usuario
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

}
