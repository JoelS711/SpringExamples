package com.joels.books_gutendex.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "author")
public class Author {

	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	 	@Column(unique = true)
	    private String name;
	    private Integer birthYear;
	    private Integer deathYear;

	    @ManyToMany(mappedBy = "author")
	    private List<Book> books;

	    public Author() {
	    }

	    public Author(AuthorInfo authorInfo) {
	        this.name = authorInfo.name();
	        this.birthYear = authorInfo.birthYear() != null ? Integer.valueOf(authorInfo.birthYear()) : null;
	        this.deathYear = authorInfo.deathYear() != null ? Integer.valueOf(authorInfo.deathYear()) : null;
	    }

	    
	    

	    public Long getId() {
			return id;
		}


		public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public Integer getBirthYear() {
	        return birthYear;
	    }

	    public void setBirthYear(Integer birthYear) {
	        this.birthYear = birthYear;
	    }

	    public Integer getDeathYear() {
	        return deathYear;
	    }

	    public void setDeathYear(Integer deathYear) {
	        this.deathYear = deathYear;
	    }

	    public List<Book> getBooks() {
	        return books;
	    }

	    public void setBooks(List<Book> books) {
	        this.books = books;
	    }
}
