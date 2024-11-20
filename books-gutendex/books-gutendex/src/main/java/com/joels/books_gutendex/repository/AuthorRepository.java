package com.joels.books_gutendex.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.joels.books_gutendex.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{

	Optional<Author> findByName(String name);
	
	@Query("SELECT a FROM Author a WHERE a.birthYear <= :year AND (a.deathYear > :year OR a.deathYear IS NULL)")
    List<Author> findAuthorsWithYear(@Param("year") int year);
}
