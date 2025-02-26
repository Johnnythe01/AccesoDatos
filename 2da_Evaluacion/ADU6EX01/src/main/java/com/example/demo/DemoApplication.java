package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Anotación que indica que es una aplicación Spring Boot
public class DemoApplication {

	public static void main(String[] args) { // Método principal
		SpringApplication.run(DemoApplication.class, args); // Arranca la aplicación
	}
}
