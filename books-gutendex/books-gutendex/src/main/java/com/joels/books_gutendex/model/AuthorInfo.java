package com.joels.books_gutendex.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AuthorInfo(@JsonAlias("name") String name, @JsonAlias("birth_year") String birthYear, @JsonAlias("death_year") String deathYear) {

}
