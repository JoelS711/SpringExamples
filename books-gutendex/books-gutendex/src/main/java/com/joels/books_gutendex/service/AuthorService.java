package com.joels.books_gutendex.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joels.books_gutendex.model.Author;
import com.joels.books_gutendex.repository.AuthorRepository;

@Service
public class AuthorService {

	private final AuthorRepository authorRepository;

    @Autowired
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
}
