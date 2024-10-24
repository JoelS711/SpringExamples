package model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown =  true)
public record DataSeason(@JsonAlias("Season") Integer number, @JsonAlias("Episodes") List<DataEpisode> episodes) {

}
