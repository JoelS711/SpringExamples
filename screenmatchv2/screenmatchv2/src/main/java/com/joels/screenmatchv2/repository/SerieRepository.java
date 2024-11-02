package com.joels.screenmatchv2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joels.screenmatchv2.model.Serie;

public interface SerieRepository extends JpaRepository<Serie, Long> {

	Optional<Serie> findByTitleContainsIgnoreCase(String nameSerie);
	List<Serie> findTop5ByOrderByRating();
}
