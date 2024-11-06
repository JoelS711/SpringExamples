package com.joels.screenmatchv2.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joels.screenmatchv2.dto.SerieDTO;
import com.joels.screenmatchv2.repository.SerieRepository;

@RestController
public class SerieController {

	@Autowired
	private SerieRepository repository;
	
	@GetMapping("/series")
	public List<SerieDTO> getAllSeries() {
		return repository.findAll().stream()
				.map(s -> new SerieDTO(s.getTitle(), s.getTotalSeasons(), s.getRating(), s.getPoster(), s.getGenre(), s.getActors(), s.getSypnosis()))
				.collect(Collectors.toList());
	}
}
