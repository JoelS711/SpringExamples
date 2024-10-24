package com.joels.screenmatchv2;



import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import main.ExampleStream;
import main.Main;
@SpringBootApplication
public class Screenmatchv2Application implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(Screenmatchv2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Main main = new Main();
		main.showMenu();
		//ExampleStream exampleStream = new ExampleStream();
		//exampleStream.showExample();
	
	}

}
