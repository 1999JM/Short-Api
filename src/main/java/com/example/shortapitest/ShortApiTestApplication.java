package com.example.shortapitest;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ShortApiTestApplication {
	public static void main(String[] args) {

		SpringApplication.run(ShortApiTestApplication.class, args);
	}

}
