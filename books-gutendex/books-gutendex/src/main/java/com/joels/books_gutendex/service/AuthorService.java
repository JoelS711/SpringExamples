package com.joels.books_gutendex.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.joels.books_gutendex.model.Author;
import com.joels.books_gutendex.repository.AuthorRepository;

@Service
public class AuthorService {

	private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    
    public Optional<Author> getAuthorByName(String name) {
        return authorRepository.findByName(name);
    }
    
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
    
    public void saveAuthor(Author author) {
    	authorRepository.save(author);
    }
    
    private void printAuthor(Author author) {
        System.out.printf("""
                ------------AUTHOR--------------
                Id: %s
                Name: %s
                Birth Year: %s
                Death Year: %s
                --------------------------------
                """,
                author.getId(),
                author.getName(),
                author.getBirthYear() != null ? author.getBirthYear() : "N/A",
                author.getDeathYear() != null ? author.getDeathYear() : "N/A"
        );
    }
    
    public void displayAllAuthors() {
    	var authors = getAllAuthors();
    	if(authors.isEmpty()) {
    		System.out.println("No authors found in the database.");
            return;
    	}
    	authors.forEach(this::printAuthor);
    }
    
    
    public List<Author> findAuthorByYear(int year){
    	return authorRepository.findAuthorsWithYear(year);
    }
    
    public void displayAuthorsByYear(int year) {
    	var authors = findAuthorByYear(year);
    	if(authors.isEmpty()) {
    		System.out.println("There are no authors with that time period.");
    	}else {
    		authors.forEach(this::printAuthor);
    	}
    }
}
