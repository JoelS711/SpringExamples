package model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episode {

	private Integer season;
	private String title;
	private Integer episodeNumber;
	private Double rating;
	private LocalDate relaseDate;
	
	
	
	public Episode(Integer season, DataEpisode d) {
		this.season = season;
		this.title = d.title();
		this.episodeNumber = d.episodeNumber();
		try {
			this.rating = Double.valueOf(d.imdbRating());
		}catch(NumberFormatException e){
			this.rating = 0.0;
		}
		
		try {
			this.relaseDate = LocalDate.parse(d.released());			
		}catch(DateTimeParseException e) {
			this.relaseDate = null;
		}
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
		return "season=" + season + ", title=" + title + ", episodeNumber=" + episodeNumber + ", rating="
				+ rating + ", relaseDate=" + relaseDate;
	}
	
	
	
}
