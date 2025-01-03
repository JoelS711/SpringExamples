package com.joels.books_gutendex.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joels.books_gutendex.model.Author;
import com.joels.books_gutendex.model.Book;
import com.joels.books_gutendex.model.Data;
import com.joels.books_gutendex.model.DataBook;
import com.joels.books_gutendex.repository.BookRepository;

@Service
public class BookService {

	private static final String URL_BASE = "https://gutendex.com/books/";
    private final ServiceAPI serviceApi;
    private final ConvertData converter;
    private final AuthorService authorService;
    private final BookRepository bookRepository;;

    @Autowired
    public BookService(ServiceAPI serviceApi, ConvertData converter, AuthorService authorService, BookRepository bookRepository) {
        this.serviceApi = serviceApi;
        this.converter = converter;
        this.authorService = authorService;
        this.bookRepository = bookRepository;
    }
    
	private DataBook getDataBook(String bookName) {
		
		var json = serviceApi.getData(URL_BASE+"?search="+bookName.replace(" ","+"));
		Data data;
		try {
			  data = converter.getData(json, Data.class);			
		}catch (Exception e) {
            throw new RuntimeException("Error parsing JSON", e);
        }
		
		var bookSearched = data.books().stream()
	            .filter(b -> b.title().toUpperCase().contains(bookName.toUpperCase()))
	            .findFirst()
	            .orElseThrow(() -> new RuntimeException("Book not found: " + bookName));
		return bookSearched;
	}
	
	 private void printBook(Book book) {
	        System.out.printf(
	            """
	            ------------BOOK--------------
	            Title: %s
	            Language: %s
	            Authors: %s
	            Downloads: %s
	            --------------------------------
	            """,
	            book.getTitle(),
	            book.getLanguage(),
	            book.getAuthor().stream()
	                .map(Author::getName)
	                .collect(Collectors.joining(", ")),
	            book.getDownloads()
	        );
	    }
	
	public void searchBook(String bookName) {
		try {
			DataBook data = getDataBook(bookName);
			List<Author> authors = data.author().stream()
					.map(authorInfo -> {
						return authorService.getAuthorByName(authorInfo.name())
								.orElseGet(() -> {
									Author newAuthor = new Author(authorInfo);
									authorService.saveAuthor(newAuthor);
									return newAuthor;
								});
					}).toList();
			Book book = new Book(data, authors);
			bookRepository.save(book);
			System.out.println(book);
		}catch (Exception e) {
	        System.out.println("An error occurred while searching for the book: " + e.getMessage());
	    }
		
	}
	
	public void listRegisteredBooks() {
	    List<Book> books = bookRepository.findAll();

	    if (books.isEmpty()) {
	        System.out.println("No books found in the database.");
	    } else {
	        books.forEach(this::printBook);
	    }
	}
	
	public List<Book> getBooksByLanguage(String language) {
	    return bookRepository.findByLanguage(language);
	}
	
	public void listBooksByLanguage(String language) {
		var books = getBooksByLanguage(language);
		if (books.isEmpty()) {
	        System.out.println("No books found for the specified language: " + language);
	        return;
	    }
		
		books.forEach(this::printBook);
	}
}
