package com.joels.screenmatchv2.model;

import java.util.List;
import java.util.OptionalDouble;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "series")
public class Serie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@Column(unique = true)
	private String title;

	private Integer totalSeasons;
	private Double rating;
	private String poster;

	@Enumerated(EnumType.STRING)
	private Category genre;
	private String actors;
	private String sypnosis;
	@Transient
	private List<Episode> episodes;

	public Serie(DataSerie dataSerie) {
		this.title = dataSerie.title();
		this.totalSeasons = dataSerie.totalSeasons();
		this.rating = OptionalDouble.of(Double.valueOf(dataSerie.rating())).orElse(0);
		this.poster = dataSerie.poster();
		this.genre = Category.fromString(dataSerie.genre().split(",")[0].trim());
		this.actors = dataSerie.actors();
		this.sypnosis = dataSerie.sypnosis();
	}

	@Override
	public String toString() {
		return "title=" + title + ", totalSeasons=" + totalSeasons + ", rating=" + rating + ", poster=" + poster
				+ ", genre=" + genre + ", actors=" + actors + ", sypnosis=" + sypnosis;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getTotalSeasons() {
		return totalSeasons;
	}

	public void setTotalSeasons(Integer totalSeasons) {
		this.totalSeasons = totalSeasons;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public Category getGenre() {
		return genre;
	}

	public void setGenre(Category genre) {
		this.genre = genre;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getSypnosis() {
		return sypnosis;
	}

	public void setSypnosis(String sypnosis) {
		this.sypnosis = sypnosis;
	}

}
