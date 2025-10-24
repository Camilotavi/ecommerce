package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {

        SpringApplication.run(EcommerceApplication.class, args);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        System.out.println("######################");
        System.out.println(" APPLICATION RUNNING");
        System.out.println("######################");
	}

}
