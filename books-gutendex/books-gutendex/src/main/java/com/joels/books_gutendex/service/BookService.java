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
		
		if (data.books() == null || data.books().isEmpty()) {
	        throw new RuntimeException("No books found in the response");
	    }
		
		var bookSearched = data.books().stream()
	            .filter(b -> b.title().toUpperCase().contains(bookName.toUpperCase()))
	            .findFirst()
	            .orElseThrow(() -> new RuntimeException("Book not found: " + bookName));
		return bookSearched;
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
	}
}
