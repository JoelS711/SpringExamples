package com.joels.apiBook;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import main.Main;

@SpringBootApplication
public class ApiBookApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ApiBookApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main();
		main.showMenu();
	}

}
