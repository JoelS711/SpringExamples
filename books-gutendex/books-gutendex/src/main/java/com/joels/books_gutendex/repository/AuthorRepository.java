package com.joels.books_gutendex.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joels.books_gutendex.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{

	Optional<Author> findByName(String name);
}
