package com.joels.books_gutendex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joels.books_gutendex.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
