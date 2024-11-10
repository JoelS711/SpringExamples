package com.joels.screenmatch_phrases.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.joels.screenmatch_phrases.model.Phrase;

public interface PhraseRepository extends JpaRepository<Phrase, Long>{

	@Query("SELECT f FROM Phrase f order by function('RANDOM') LIMIT 1")
	public Phrase getRandomPhrase();

	
}
