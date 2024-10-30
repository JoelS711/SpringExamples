package com.joels.screenmatchv2.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataSerie(@JsonAlias("Title") String title, @JsonAlias("totalSeasons") Integer totalSeasons,
		@JsonAlias("imdbRating") String rating, @JsonAlias("Poster") String poster, @JsonAlias("Genre") String genre,
		@JsonAlias("Actors") String actors, @JsonAlias("Plot") String sypnosis) {

}
