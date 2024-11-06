//package com.joels.screenmatchv2;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//import com.joels.screenmatchv2.main.Principal;
//import com.joels.screenmatchv2.repository.SerieRepository;
//
//@SpringBootApplication
//public class Screenmatchv2Application2Consola implements CommandLineRunner {
//
//	
//	@Autowired
//	private SerieRepository repository;
//	public static void main(String[] args) {
//		SpringApplication.run(Screenmatchv2Application2Consola.class, args);
//		
//	}
//
//	@Override
//	public void run(String... args) throws Exception {
//
//		// Main main = new Main();
//		// main.showMenu();
//		// ExampleStream exampleStream = new ExampleStream();
//		// exampleStream.showExample();
//		Principal principal = new Principal(repository);
//		principal.showMenu();
//
//	}
//
//}
