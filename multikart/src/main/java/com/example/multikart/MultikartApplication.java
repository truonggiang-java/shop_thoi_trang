package com.example.multikart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MultikartApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultikartApplication.class, args);
		
		
	}

}
