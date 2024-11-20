package com.joels.books_gutendex.main;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joels.books_gutendex.service.AuthorService;
import com.joels.books_gutendex.service.BookService;

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
				bookService.listRegisteredBooks();
				break;
			case 3:
				authorService.displayAllAuthors();;
				break;
			case 4:
				System.out.println("Enter the year: ");
				var year = keyboard.nextInt();
				authorService.displayAuthorsByYear(year);
				break;
			case 5:
				System.out.println("""
				        Enter the language to search for books:
				        Spanish (es)
				        English (en)
				        Italian (it)
				        Portuguese (pt)
				        Russian (ru)
				    """);
				String languageCode = keyboard.nextLine().trim().toLowerCase();
				List<String> validLanguages = List.of("es", "en", "it", "pt", "ru");
			    if (!validLanguages.contains(languageCode)) {
			        System.out.println("Invalid language code. Please try again.");
			        break;
			    }
			    bookService.listBooksByLanguage(languageCode);
				break;
			case 0:
				System.out.println("Exiting...");
				break;
			default:
				System.out.println("Invalid Option");
			}
		}
	}
	
	
}
