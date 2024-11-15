package com.joels.books_gutendex.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	private String title;
	
	@ManyToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Author> author;
	private String language;
	private Long downloads;
	
	public Book() {
	}
	
	public Book(DataBook dataBook) {
		this.title = dataBook.title();
		this.author = dataBook.author();
		this.language = dataBook.language().get(0);
		this.downloads = dataBook.downloadsCount();
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
	
	
	
	public List<AuthorInfo> getAuthor() {
		return author;
	}

	public void setAuthor(List<AuthorInfo> author) {
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
		return "Book [title=" + title + ", author=" + author + ", language=" + language + ", downloads=" + downloads
				+ "]";
	}
	
	
		
	
}
