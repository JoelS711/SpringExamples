package com.joels.screenmatchv2.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "episodes")
public class Episode {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private Integer season;
	private String title;
	private Integer episodeNumber;
	private Double rating;
	private LocalDate relaseDate;
	@ManyToOne
	private Serie serie;

	public Episode() {
	}

	public Episode(Integer season, DataEpisode d) {
		this.season = season;
		this.title = d.title();
		this.episodeNumber = d.episodeNumber();
		try {
			this.rating = Double.valueOf(d.imdbRating());
		} catch (NumberFormatException e) {
			this.rating = 0.0;
		}

		try {
			this.relaseDate = LocalDate.parse(d.released());
		} catch (DateTimeParseException e) {
			this.relaseDate = null;
		}
	}

	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}

	public Integer getSeason() {
		return season;
	}

	public void setSeason(Integer season) {
		this.season = season;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getEpisodeNumber() {
		return episodeNumber;
	}

	public void setEpisodeNumber(Integer episodeNumber) {
		this.episodeNumber = episodeNumber;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public LocalDate getRelaseDate() {
		return relaseDate;
	}

	public void setRelaseDate(LocalDate relaseDate) {
		this.relaseDate = relaseDate;
	}

	@Override
	public String toString() {
		return "season=" + season + ", title=" + title + ", episodeNumber=" + episodeNumber + ", rating=" + rating
				+ ", relaseDate=" + relaseDate;
	}

}
