package com.proyect.ventas;

import com.proyect.ventas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class VentasApplication {

	public static void main(String[] args) {
		SpringApplication.run(VentasApplication.class, args);
	}




}
