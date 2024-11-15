package com.joels.books_gutendex.main;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joels.books_gutendex.model.Book;
import com.joels.books_gutendex.model.DataBook;
import com.joels.books_gutendex.repository.BookRepository;
import com.joels.books_gutendex.service.ConvertData;
import com.joels.books_gutendex.service.ServiceAPI;

@Component
public class Main {

	private static final String URL_BASE = "https://gutendex.com/books/";
	private ServiceAPI serviceApi = new ServiceAPI();
	private ConvertData converter = new ConvertData();
	private BookRepository repository;
	private Scanner keyboard = new Scanner(System.in);
	
	public Main(BookRepository repository) {
		this.repository = repository;
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
		DataBook data = converter.getData(json, DataBook.class);
		return data;
	}
	
	public void searchBook() {
		DataBook data = getDataBook();
		Book book = new Book(data);
		repository.save(book);
		System.out.println(data);
	}
}
