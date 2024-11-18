package com.joels.books_gutendex.main;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joels.books_gutendex.model.Author;
import com.joels.books_gutendex.model.Book;
import com.joels.books_gutendex.model.Data;
import com.joels.books_gutendex.model.DataBook;
import com.joels.books_gutendex.repository.AuthorRepository;
import com.joels.books_gutendex.repository.BookRepository;
import com.joels.books_gutendex.service.AuthorService;
import com.joels.books_gutendex.service.BookService;
import com.joels.books_gutendex.service.ConvertData;
import com.joels.books_gutendex.service.ServiceAPI;

@Component
public class Main {

	
	private final BookService bookService; 
    private final AuthorService authorService; 
    private Scanner keyboard = new Scanner(System.in);
	
    @Autowired
    public Main(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
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
				System.out.println("name of the book to search: ");
				var bookName = keyboard.nextLine();
				bookService.searchBook(bookName);
				break;
			case 2:
				//listRegisteredBooks();
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
	
	
	
	
	
	/*public void listRegisteredBooks() {
	    List<Book> books = repository.findAll();

	    if (books.isEmpty()) {
	        System.out.println("No books found in the database.");
	    } else {
	        books.forEach(book -> {
	            System.out.println("------------BOOK--------------");
	            System.out.println("Title: " + book.getTitle());
	            String authors = book.getAuthor().stream()
	                    .map(Author::getName)
	                    .collect(Collectors.joining(", "));
	            System.out.println("Author: " + authors);
	            System.out.println("Downloads: " + book.getDownloads());
	            System.out.println("Language: " + book.getLanguage());
	            System.out.println("--------------------------------");
	        });
	    }
	}*/
}
