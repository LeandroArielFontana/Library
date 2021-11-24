package com.example.Libreria3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Libreria3Application {

	public static void main(String[] args) {
		SpringApplication.run(Libreria3Application.class, args);
	}

}
