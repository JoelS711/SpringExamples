package com.joels.screenmatchv2.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown =  true)
public record DataEpisode(@JsonAlias("Title") String title,@JsonAlias("Episode") Integer episodeNumber,@JsonAlias("imdbRating") String imdbRating,@JsonAlias("Released") String released) {

}
