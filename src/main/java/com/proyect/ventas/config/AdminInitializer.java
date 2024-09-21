package com.proyect.ventas.config;

import com.proyect.ventas.models.User;
import com.proyect.ventas.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminInitializer {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public AdminInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner createAdminUser(){
        return args -> {
            //verificamos si hay usuarios en la DB
            if (userRepository.count() == 0){
                //creamos un nuevo usuario
                User admin = new User();
                admin.setName("Jesus");
                admin.setLastname("Anaya");
                admin.setUsername("jesus");
                admin.setPassword(passwordEncoder.encode("admin123456"));
                admin.setRoles("ADMIN");
                userRepository.save(admin);
                System.out.printf("Usuario Creado Con Exito");
            } else {
                System.out.printf("Ya existe suarios en la DB");
            }
        };
    }
}
