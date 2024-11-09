package com.joels.screenmatchv2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joels.screenmatchv2.dto.EpisodeDTO;
import com.joels.screenmatchv2.dto.SerieDTO;
import com.joels.screenmatchv2.service.SerieService;

@RestController
@RequestMapping("/series")
public class SerieController {

	@Autowired
	private SerieService serieService;
	
	@GetMapping()
	public List<SerieDTO> getAllSeries() {
		return serieService.getAllSeries();
	}
	
	@GetMapping("/top5")
	public List<SerieDTO> getTop5(){
		return serieService.getTop5();
	}
	
	@GetMapping("/lanzamientos")
	public List<SerieDTO> getMostRecentReleases(){
		return serieService.getMostRecentReleases();
	}
	
	@GetMapping("/{id}")
	public SerieDTO getById(@PathVariable Long id) {
		return serieService.getById(id);
	}
	
	@GetMapping("/{id}/seasons/all")
	public List<EpisodeDTO> getAllSeasons(@PathVariable Long id) {
		return serieService.getAllSeasons(id);
	}
	
	@GetMapping("/{id}/seasons/{seasonNumber}")
	public List<EpisodeDTO> getSeasonByNumber(@PathVariable Long id, @PathVariable Long seasonNumber){
		return serieService.getSeasonByNumber(id, seasonNumber);
	}
	
	@GetMapping("/categoria/{genre}")
	public List<SerieDTO> getSerieByCategory(@PathVariable String genre){
		return serieService.getSerieByCategory(genre);
	}
}
