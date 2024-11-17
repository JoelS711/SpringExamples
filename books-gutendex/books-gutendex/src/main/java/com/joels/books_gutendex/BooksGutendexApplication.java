package com.joels.books_gutendex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.joels.books_gutendex.main.Main;
import com.joels.books_gutendex.repository.AuthorRepository;
import com.joels.books_gutendex.repository.BookRepository;


@SpringBootApplication
public class BooksGutendexApplication implements CommandLineRunner{

	@Autowired
	private BookRepository repository;
	
	@Autowired
	private AuthorRepository authorRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(BooksGutendexApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(repository, authorRepo);
		main.showMenu();
	}

}
