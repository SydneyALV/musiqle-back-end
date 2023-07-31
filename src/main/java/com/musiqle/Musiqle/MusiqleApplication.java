package com.musiqle.Musiqle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.ComponentScan;

// @ComponentScan("com.musiqle.Musiqle.UserRepository")
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
// @SpringBootApplication
// @Configuration
// @EnableJpaRepositories(basePackages = "com.musiqle.Musiqle.UserRepository")

public class MusiqleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusiqleApplication.class, args);
	}

}
