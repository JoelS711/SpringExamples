package com.joels.screenmatch_phrases.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joels.screenmatch_phrases.dto.PhraseDTO;
import com.joels.screenmatch_phrases.model.Phrase;
import com.joels.screenmatch_phrases.repository.PhraseRepository;

@Service
public class PhraseService {

	@Autowired
	private PhraseRepository repository;
	
	public PhraseDTO getRandomPhrase() {
		Phrase phrase = repository.getRandomPhrase();
		return new PhraseDTO(phrase.getTitle(), phrase.getPhrase(), phrase.getCharacter(), phrase.getPoster());
	}

	
}
