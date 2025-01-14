package com.example.learnSpringBoot.springTutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
public class SpringLearnApplication{
	public static void main(String[] args) {
		SpringApplication.run(SpringLearnApplication.class, args);
	}
}
