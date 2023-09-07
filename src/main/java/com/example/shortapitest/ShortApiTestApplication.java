package com.example.shortapitest;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShortApiTestApplication {
	@PersistenceContext
	private EntityManager entityManager;
	public static void main(String[] args) {


		SpringApplication.run(ShortApiTestApplication.class, args);
	}

}
