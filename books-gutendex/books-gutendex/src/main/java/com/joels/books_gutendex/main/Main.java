package com.joels.books_gutendex.main;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joels.books_gutendex.model.Author;
import com.joels.books_gutendex.model.Book;
import com.joels.books_gutendex.model.Data;
import com.joels.books_gutendex.model.DataBook;
import com.joels.books_gutendex.repository.AuthorRepository;
import com.joels.books_gutendex.repository.BookRepository;
import com.joels.books_gutendex.service.ConvertData;
import com.joels.books_gutendex.service.ServiceAPI;

@Component
public class Main {

	private static final String URL_BASE = "https://gutendex.com/books/";
	private ServiceAPI serviceApi = new ServiceAPI();
	private ConvertData converter = new ConvertData();
	private BookRepository repository;
	private AuthorRepository authorRepository;
	private Scanner keyboard = new Scanner(System.in);
	
	public Main(BookRepository repository, AuthorRepository authorRepo) {
		this.repository = repository;
		this.authorRepository = authorRepo;
	}
	
	public void showMenu() {
		var option = -1;
		while (option != 0) {
			var menu = """
					1. Search book by title
					2. List registered books
					3. List registered authors
					4. List authors alive in a given year
					5. List books by language

					0. Exit
					""";
			System.out.println(menu);
			option = keyboard.nextInt();
			keyboard.nextLine();

			switch (option) {
			case 1:
				searchBook();
				break;
			case 2:
				
				break;
			case 3:
				
				break;
			case 4:
				
				break;
			case 5:
				
				break;
			case 0:
				System.out.println("Exiting...");
				break;
			default:
				System.out.println("Invalid Option");
			}
		}
	}
	
	private DataBook getDataBook() {
		System.out.println("name of the book to search: ");
		var bookName = keyboard.nextLine();
		var json = serviceApi.getData(URL_BASE+"?search="+bookName.replace(" ","+"));
		Data data;
		try {
			  data = converter.getData(json, Data.class);			
		}catch (Exception e) {
            throw new RuntimeException("Error parsing JSON", e);
        }
		
		if (data.books() == null || data.books().isEmpty()) {
	        throw new RuntimeException("No books found in the response");
	    }
		
		var bookSearched = data.books().stream()
	            .filter(b -> b.title().toUpperCase().contains(bookName.toUpperCase()))
	            .findFirst()
	            .orElseThrow(() -> new RuntimeException("Book not found: " + bookName));
		return bookSearched;
	}
	
	public void searchBook() {
		DataBook data = getDataBook();
		List<Author> authors = data.author().stream()
				.map(authorInfo -> {
					return authorRepository.findByName(authorInfo.name())
							.orElseGet(() -> {
								Author newAuthor = new Author(authorInfo);
								authorRepository.save(newAuthor);
								return newAuthor;
							});
				}).toList();
		Book book = new Book(data, authors);
		repository.save(book);
		System.out.println(book.toString());
	}
}
