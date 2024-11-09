package com.joels.screenmatchv2.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joels.screenmatchv2.dto.EpisodeDTO;
import com.joels.screenmatchv2.dto.SerieDTO;
import com.joels.screenmatchv2.model.Category;
import com.joels.screenmatchv2.model.Serie;
import com.joels.screenmatchv2.repository.SerieRepository;

@Service
public class SerieService {

	@Autowired
	private SerieRepository repository;
	
	public List<SerieDTO> getAllSeries() {
		return convertData(repository.findAll());
	}

	public List<SerieDTO> getTop5() {
		return convertData(repository.findTop5ByOrderByRatingDesc());
	}
	
	public List<SerieDTO> getMostRecentReleases(){
		return convertData(repository.mostRecentReleases());
	}
	
	public List<SerieDTO> convertData(List<Serie> serie){
		return serie.stream()
		.map(s -> new SerieDTO(s.getId(), s.getTitle(), s.getTotalSeasons(), s.getRating(), s.getPoster(), s.getGenre(), s.getActors(), s.getSynopsis()))
		.collect(Collectors.toList());
	}

	public SerieDTO getById(Long id) {
		Optional<Serie> serie = repository.findById(id);
		if(serie.isPresent()) {
			Serie s = serie.get();
			return new SerieDTO(s.getId(), s.getTitle(), s.getTotalSeasons(), s.getRating(), s.getPoster(), s.getGenre(), s.getActors(), s.getSynopsis());
		}
		return null;
	}

	public List<EpisodeDTO> getAllSeasons(Long id) {
		Optional<Serie> serie = repository.findById(id);
		if(serie.isPresent()) {
			Serie s = serie.get();
			return s.getEpisodes().stream().map(e -> new EpisodeDTO(e.getSeason(), e.getTitle(), e.getEpisodeNumber()))
					.collect(Collectors.toList());
		}
		return null;
	}

	public List<EpisodeDTO> getSeasonByNumber(Long id, Long seasonNumber) {
		return repository.getSeasonByNumber(id, seasonNumber).stream()
				.map(e -> new EpisodeDTO(e.getSeason(), e.getTitle(), e.getEpisodeNumber()))
				.collect(Collectors.toList());
	}

	public List<SerieDTO> getSerieByCategory(String genre) {
		Category category = Category.fromSpanish(genre);
		return convertData(repository.findByGenre(category));
	}
}
