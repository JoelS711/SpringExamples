package com.joels.screenmatch_phrases.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joels.screenmatch_phrases.dto.PhraseDTO;
import com.joels.screenmatch_phrases.service.PhraseService;

@RestController
public class PhraseController {

	@Autowired
	PhraseService service;
	
	@GetMapping("/series/frases")
	public PhraseDTO getRandomPhrase() {
		return service.getRandomPhrase();
	}
}
