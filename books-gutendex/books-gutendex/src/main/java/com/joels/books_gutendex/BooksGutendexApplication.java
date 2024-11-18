package com.joels.books_gutendex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.joels.books_gutendex.main.Main;
import com.joels.books_gutendex.repository.AuthorRepository;
import com.joels.books_gutendex.repository.BookRepository;
import com.joels.books_gutendex.service.AuthorService;
import com.joels.books_gutendex.service.BookService;


@SpringBootApplication
public class BooksGutendexApplication implements CommandLineRunner{

	@Autowired
	private BookService bookService;
	
	@Autowired
	private AuthorService authorService;
	
	public static void main(String[] args) {
		SpringApplication.run(BooksGutendexApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(bookService, authorService);
		main.showMenu();
	}

}
