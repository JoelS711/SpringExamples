package com.joels.books_gutendex.model;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	private String title;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
	private List<Author> author;
	private String language;
	private Long downloads;
	
	public Book() {
	}
	
	public Book(DataBook dataBook, List<Author> authors) {
		this.title = dataBook.title();
		this.language = dataBook.language().get(0);
		this.downloads = dataBook.downloadsCount();
		this.author = authors;
	}
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public List<Author> getAuthor() {
		return author;
	}

	public void setAuthor(List<Author> author) {
		this.author = author;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Long getDownloads() {
		return downloads;
	}
	public void setDownloads(Long downloads) {
		this.downloads = downloads;
	}

	@Override
	public String toString() {
		String authorName = author.stream()
				.map(Author::getName)
				.collect(Collectors.joining(", "));
		return "------------BOOK--------------\n" +
        "Title: " + title + "\n" +
        "Author: " + authorName + "\n" +
        "Downloads: " + downloads + "\n" +
        "Language: " + language + "\n" +
        "--------------------------------";
	}
	
	
		
	
}
